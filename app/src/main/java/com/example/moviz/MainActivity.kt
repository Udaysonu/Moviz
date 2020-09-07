package com.example.moviz

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private var lstSlide=ArrayList<slide>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lstSlide.add(slide(R.drawable.uma,"Movie1"))
        lstSlide.add(slide(R.drawable.avatar,"Movie2"))
        lstSlide.add(slide(R.drawable.uma,"Movie1"))
        lstSlide.add(slide(R.drawable.avatar,"Movie2"))
        var adaptr=slideAdapter(this,lstSlide)
        viewpager.adapter=adaptr
        indicator.setupWithViewPager(viewpager,true)


        //RecyclerView setup
        val lstMovies=ArrayList<Movie>()
        lstMovies.add(Movie("Uma",R.drawable.avatar))
        lstMovies.add(Movie("Ra-1",R.drawable.js))
        lstMovies.add(Movie("Uma",R.drawable.avatar))
        lstMovies.add(Movie("Ra-1",R.drawable.js))
        lstMovies.add(Movie("Uma",R.drawable.avatar))
        lstMovies.add(Movie("Ra-1",R.drawable.js))
        lstMovies.add(Movie("Uma",R.drawable.avatar))
        lstMovies.add(Movie("Ra-1",R.drawable.js))
        rv_movies.adapter=MovieAdapter(this,lstMovies,this)
        val linearlayout=LinearLayoutManager(this)
        linearlayout.orientation=LinearLayoutManager.HORIZONTAL
        rv_movies.layoutManager=linearlayout
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun onMovieclick(titl:String, thumb:Int, imageview:ImageView){
        val intent= Intent(this,Movie_details::class.java)
        intent.putExtra("MOV_TITLE",titl)
        intent.putExtra("MOV_THUMB",thumb)
        val options=ActivityOptions.makeSceneTransitionAnimation(this,imageview,"sharedName")
        startActivity(intent,options.toBundle())
    }


    class slider : TimerTask() {

        override fun run() {


        }

    }
}