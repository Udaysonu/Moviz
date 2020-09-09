package com.example.moviz.classes

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moviz.fragments.Bollywood_fragment
import com.example.moviz.fragments.Hollywood_fragment
import com.example.moviz.fragments.Tollywood_fragment

class Home_tab_fragment_adapter(val mcontext: Context, val fm:FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
    when(position)
    {
        0->{return Hollywood_fragment()}
        1->{return Bollywood_fragment()}
        2->{return Tollywood_fragment()}
       else->{return Tollywood_fragment()}
    }
     }

    override fun getCount(): Int {
       return 3
    }

}