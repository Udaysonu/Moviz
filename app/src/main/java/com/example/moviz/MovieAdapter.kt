package com.example.moviz

import android.content.Context
import android.os.Build
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlin.coroutines.coroutineContext

class MovieAdapter(
    val mcontext: Context,
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
        holder.itemView.mov_img.setImageResource(mData[position].thumbnail)
        holder.itemView.mov_title.text=mData[position].title
        holder.itemView.setOnClickListener {
            movieclicklistener.onMovieclick(mData[position].title,mData[position].thumbnail,holder.itemView.mov_img)
        }
    }
}