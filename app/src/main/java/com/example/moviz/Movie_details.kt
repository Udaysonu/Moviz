package com.example.moviz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_movie_details.*

class Movie_details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val mov_title=intent.getStringExtra("MOV_TITLE")
        val mov_thumb=intent.getIntExtra("MOV_THUMB",-1)
        if(mov_thumb!=-1)
        {
            mov_detail_img.setImageResource(mov_thumb)
        }

    }
}