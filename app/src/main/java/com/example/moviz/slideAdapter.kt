package com.example.moviz

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.slide_item.view.*

class slideAdapter(private val mcontext:Context, private val mlstSlides:ArrayList<slide>): RecyclerView.Adapter<slideAdapter.slideViewholder>() {
     class slideViewholder(itemView: View) :RecyclerView.ViewHolder(itemView){
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): slideViewholder {
         val slideitem=LayoutInflater.from(parent.context).inflate(R.layout.slide_item,parent,false)
        return slideViewholder(slideitem)
        }

    override fun getItemCount(): Int {
      return  mlstSlides.size
    }

    override fun onBindViewHolder(holder: slideViewholder, position: Int) {
        holder.itemView.slide_img.setImageResource(mlstSlides[position].image)
        holder.itemView.slide_text.text = mlstSlides[position].title
    }

}