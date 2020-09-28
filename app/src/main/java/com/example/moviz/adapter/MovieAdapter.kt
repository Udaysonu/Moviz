package com.example.moviz.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.moviz.activities.MainActivity
import com.example.moviz.classes.Movie
import com.example.moviz.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(

    val mData:ArrayList<Movie>,
    val movieclicklistener: MainActivity
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

     class MovieViewHolder(itemview:View): RecyclerView.ViewHolder(itemview) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false)
        return MovieViewHolder(view)
     }

    override fun getItemCount(): Int {
      return mData.size
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Picasso.get().load(mData[position].thumbnail).placeholder(R.drawable.mov_back).error(R.drawable.mov_back).into(holder.itemView.mov_img)
        holder.itemView.mov_title.text=mData[position].title
        holder.itemView.setOnClickListener {
        val check=movieclicklistener
            if(check!=null)
            {            movieclicklistener.onMovieclick(mData[position].title,mData[position].thumbnail,mData[position].coverPhoto,mData[position].streamLink,holder.itemView.mov_img)
            }

    }}
}