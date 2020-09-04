package com.example.moviz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

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
       val layoutmanager=LinearLayoutManager(this)
        layoutmanager.orientation=LinearLayoutManager.HORIZONTAL
        viewpager.layoutManager=layoutmanager
        viewpager.adapter=adaptr

    }
}