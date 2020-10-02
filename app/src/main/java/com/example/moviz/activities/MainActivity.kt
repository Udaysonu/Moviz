package com.example.moviz.activities

import android.app.ActionBar
import android.app.ActivityOptions
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.media.MediaCodec.MetricsConstants.ROTATION
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviz.classes.Movie
import com.example.moviz.R
import com.example.moviz.adapter.MovieAdapter
import com.example.moviz.adapter.slideAdapter
import com.example.moviz.classes.Home_tab_fragment_adapter
import com.example.moviz.classes.slide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.GRAVITY_FILL
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(){

    lateinit var My_Mov_Adapter:MovieAdapter

    // this is for displaying " loading please wait" when our app is fetching information from Firebase.Database;
    lateinit var loadingDIalog:AlertDialog;

    // constant to hold FirebaseDatabase
    val database by lazy{
        FirebaseFirestore.getInstance()
    }

    // Array to store all the slides details that are displayed in slider ...
    val lstSlide=ArrayList<slide>();


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // this displays a dialog box telling " loading please wait "  while our app is fetching information from Firebasedatabase;
        // we are using "loadingDialog" so that after our data loaded we are going to dismiss it
        // "loadingDialog" is dismissed in another function (intiRV_pop_movies)
        loadingDIalog= AlertDialog.Builder(this)
           .setTitle("Loading")
           .setMessage("Please Wait")
           .setCancelable(false)
           .show()

        //slider setup
        initSlider()

        //more popular movies RecyclerView setup
        intiRV_pop_movies()

        //Init tab view
        initTabView()

        // this timer object helps in running a function after each interval
        val timer= Timer();
        timer.scheduleAtFixedRate(SliderTimer(),4000,4000);

    }

    // This funciton is used to swap the slides
    inner class SliderTimer : TimerTask() {
        override fun run() {
             runOnUiThread {
                 if(viewpager.currentItem<lstSlide.size-1)
                 {
                     viewpager.currentItem=viewpager.currentItem+1;
                 }
                 else
                 {
                     viewpager.currentItem=0;
                 }
             }
        }

    }





    // this function initializes tab view in our mainActivity.
    private fun initTabView() {
        // home_tab.setupWithViewPager(home_tab_viewpager)

        val myAdapter=Home_tab_fragment_adapter(this,supportFragmentManager)
        home_tab.addTab(home_tab.newTab().setText("Hollywood"))
        home_tab.addTab(home_tab.newTab().setText("Bollywood"))
        home_tab.addTab(home_tab.newTab().setText("Tollywood"))

        home_tab.tabGravity= GRAVITY_FILL




            home_tab_viewpager.adapter=myAdapter
            home_tab_viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(home_tab))

            // when ever a tab is selected the viewpager is filled with corresponding fragment
            home_tab.addOnTabSelectedListener(object:OnTabSelectedListener{
                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    // viewpager is filled with corresponding fragment
                    home_tab_viewpager.currentItem=tab!!.position
                }
            })
    }






    // this function initializes the slider in our mainActivity
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initSlider() {
        indicator.setupWithViewPager(viewpager,true)

        var adaptr= slideAdapter(this, lstSlide)
        viewpager.adapter=adaptr

        with(Dispatchers.IO){
            database.collection("slider").get().addOnCompleteListener {

                for(i in it.result!!.documents)
                {
                    val slider_details=i.toObject(slide::class.java)!!
                    lstSlide.add(slider_details)
                }
                // this runonUithread is necessary because UI related operation cannot be done on IO thread
                runOnUiThread { adaptr.notifyDataSetChanged() }
            }
        }

    }


    // this funciton fills popular movies row with movies
    private fun intiRV_pop_movies() {


        val lstMovies=ArrayList<Movie>()

        My_Mov_Adapter= MovieAdapter( lstMovies, this)

        rv_movies.adapter=My_Mov_Adapter
        val linearlayout=LinearLayoutManager(this)
        linearlayout.orientation=LinearLayoutManager.HORIZONTAL
        rv_movies.layoutManager=linearlayout


        with(Dispatchers.IO) {
            database.collection("movies").get().addOnCompleteListener{
                loadingDIalog.dismiss()
                for(i in it.result!!.documents)
                {
                    val mov_detail=i.toObject(Movie::class.java)!!
                    lstMovies.add(mov_detail)
                }
//                Toast.makeText(this,"welcome",Toast.LENGTH_LONG).show()
                // this runonUithread is necessary because UI related operation cannot be done on IO thread

                runOnUiThread {  My_Mov_Adapter.notifyDataSetChanged() }
            }
        }
    }



// this function is the extension of the MovieAdapter
// when on a particular movie is clicked we are opening the movie details with it
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun onMovieclick(titl:String, thumb: String, coverImage: String,movlink:String, imageview:ImageView){
        val intent= Intent(this, Movie_details::class.java)
        intent.putExtra("MOV_TITLE",titl)
        intent.putExtra("MOV_THUMB",thumb)
        intent.putExtra("MOV_COVER",coverImage)
        intent.putExtra("MOV_LINK",movlink)
        val options=ActivityOptions.makeSceneTransitionAnimation(this,imageview,"sharedName")
        startActivity(intent,options.toBundle())


    }

    // App menu bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater=menuInflater

        inflater.inflate(R.menu.menu_bar,menu)
        return true;
    }

    // on particular option of menu is selected open corresponding intent
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.movie_request->{
                startActivity(Intent(this,RequestMovieActivity::class.java))
                return true;
            }

        }
        return super.onOptionsItemSelected(item)

    }

}

