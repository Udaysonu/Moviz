package com.example.moviz.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviz.R
import com.example.moviz.adapter.CastAdapter
import com.example.moviz.classes.Cast
import kotlinx.android.synthetic.main.activity_movie_details.*

class Movie_details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val mov_title=intent.getStringExtra("MOV_TITLE")
        val mov_thumb=intent.getIntExtra("MOV_THUMB",-1)
        val mov_coverPhoto=intent.getIntExtra("MOV_COVER",-1)
        supportActionBar?.title=mov_title
        mov_detail_title.text=mov_title

        if(mov_thumb!=-1)
        {
            mov_detail_back.setImageResource(mov_coverPhoto)
            mov_detail_img.setImageResource(mov_thumb)
        }

        mov_detail_back.animation=AnimationUtils.loadAnimation(this,
            R.anim.scale_animation
        )

        mov_detail_fab.animation=AnimationUtils.loadAnimation(this,
            R.anim.scale_animation
        )
        mov_detail_fab.setOnClickListener{
            startActivity(Intent(this,MoviePlayerActivity::class.java))
        }

        inflateCastRV()
    }

    private fun inflateCastRV() {
        val lstcast=ArrayList<Cast>()
        lstcast.add(Cast("NTR",R.drawable.anatha2))
        lstcast.add(Cast("ALLU Arjun",R.drawable.ala))
        lstcast.add(Cast("Vijay",R.drawable.gee))
        lstcast.add(Cast("Mahesh",R.drawable.sri))

        rv_cast.adapter=CastAdapter(this,lstcast)
        val lm=LinearLayoutManager(this)
        lm.orientation=LinearLayoutManager.HORIZONTAL
        rv_cast.layoutManager=lm
    }
}