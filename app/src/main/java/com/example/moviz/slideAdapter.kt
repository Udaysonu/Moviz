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

class slideAdapter(private val mcontext:Context, private val mlstSlides:ArrayList<slide>): PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
        }

    override fun getCount(): Int {
        return mlstSlides.size
     }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var inflater= mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var slideLayout=inflater.inflate(R.layout.slide_item,null)
        slideLayout.slide_img.setImageResource(mlstSlides[position].image)
        slideLayout.slide_text.text=mlstSlides[position].title
        container.addView(slideLayout)
        return slideLayout
    }

}