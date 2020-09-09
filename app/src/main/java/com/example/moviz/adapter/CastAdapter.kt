package com.example.moviz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.recyclerview.widget.RecyclerView
import com.example.moviz.R
import com.example.moviz.classes.Cast
import kotlinx.android.synthetic.main.cast_item.view.*
import java.util.zip.Inflater

class CastAdapter(val mcontext: Context, val mData:ArrayList<Cast>): RecyclerView.Adapter<CastAdapter.castViewHolder>() {
    class castViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    }



    override fun getItemCount(): Int {
     return   mData.size
    }



    override fun onBindViewHolder(holder: castViewHolder, position: Int) {
        holder.itemView.cast_title.text=mData[position].name
        holder.itemView.cast_img.setImageResource(mData[position].image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): castViewHolder {
        val itemview=LayoutInflater.from(parent.context).inflate(R.layout.cast_item,parent,false)
        return castViewHolder(itemview)
    }
}