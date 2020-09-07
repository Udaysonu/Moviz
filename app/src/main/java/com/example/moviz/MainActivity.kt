package com.example.moviz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        rv_movies.adapter=MovieAdapter(this,lstMovies)
        val linearlayout=LinearLayoutManager(this)
        linearlayout.orientation=LinearLayoutManager.HORIZONTAL
        rv_movies.layoutManager=linearlayout
    }
    class slider : TimerTask() {

        override fun run() {


        }

    }
}