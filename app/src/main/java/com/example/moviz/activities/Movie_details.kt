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
import com.example.moviz.classes.Movie
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_details.*

class Movie_details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val mov_title=intent.getStringExtra("MOV_TITLE")
        val mov_thumb=intent.getStringExtra("MOV_THUMB")
        val mov_coverPhoto=intent.getStringExtra("MOV_COVER")
        val mov_stream_link=intent.getStringExtra("MOV_LINK")
        supportActionBar?.title=mov_title
        mov_detail_title.text=mov_title


            Picasso.get().load(mov_coverPhoto).into(mov_detail_back)
            Picasso.get().load(mov_thumb).into(mov_detail_img)

        mov_detail_back.animation=AnimationUtils.loadAnimation(this,
            R.anim.scale_animation
        )

        mov_detail_fab.animation=AnimationUtils.loadAnimation(this,
            R.anim.scale_animation
        )
        mov_detail_fab.setOnClickListener{
            val intent=Intent(this,MoviePlayerActivity::class.java)
            intent.putExtra("MOV_LINK",mov_stream_link)
            startActivity(intent)
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