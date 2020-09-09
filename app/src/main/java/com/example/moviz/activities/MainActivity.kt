package com.example.moviz.activities

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviz.classes.Movie
import com.example.moviz.R
import com.example.moviz.adapter.MovieAdapter
import com.example.moviz.adapter.slideAdapter
import com.example.moviz.classes.slide
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private var lstSlide=ArrayList<slide>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //slider setup
        initSlider()

        //more popular movies RecyclerView setup
        intiRV_pop_movies()

        //initTabView()
    }

    private fun initSlider() {


        lstSlide.add(
            slide(
                R.drawable.ala2,
                "Ala Vaikuntapuramulo"
            )
        )
        lstSlide.add(
            slide(
                R.drawable.sri,
                "Srimanthudu"
            )
        )
        lstSlide.add(
            slide(
                R.drawable.gee,
                "Geetha Govindham"
            )
        )
        lstSlide.add(
            slide(
                R.drawable.anatha,
                "Janatha Garage"
            )
        )
        var adaptr= slideAdapter(this, lstSlide)
        viewpager.adapter=adaptr
        indicator.setupWithViewPager(viewpager,true)

    }

    private fun intiRV_pop_movies() {


        val lstMovies=ArrayList<Movie>()
        lstMovies.add(
            Movie(
                "Janatha Garage",
                R.drawable.anatha,
                R.drawable.anatha2
            )
        )
        lstMovies.add(
            Movie(
                "Geetha Govindham",
                R.drawable.gee,
                R.drawable.gee2
            )
        )
        lstMovies.add(
            Movie(
                "Ala Vaikuntapuramulo",
                R.drawable.ala,
                R.drawable.ala2
            )
        )
        lstMovies.add(
            Movie(
                "Srimanthudu",
                R.drawable.sri,
                R.drawable.sri2
            )
        )

        rv_movies.adapter=
            MovieAdapter(this, lstMovies, this)
        val linearlayout=LinearLayoutManager(this)
        linearlayout.orientation=LinearLayoutManager.HORIZONTAL
        rv_movies.layoutManager=linearlayout
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun onMovieclick(titl:String, thumb:Int,coverImage:Int, imageview:ImageView){
        val intent= Intent(this, Movie_details::class.java)
        intent.putExtra("MOV_TITLE",titl)
        intent.putExtra("MOV_THUMB",thumb)
        intent.putExtra("MOV_COVER",coverImage)
        val options=ActivityOptions.makeSceneTransitionAnimation(this,imageview,"sharedName")
        startActivity(intent,options.toBundle())
    }


    class slider : TimerTask() {

        override fun run() {


        }

    }
}