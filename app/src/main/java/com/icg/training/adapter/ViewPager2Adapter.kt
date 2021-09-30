package com.icg.training.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.icg.training.Constants
import com.icg.training.TabFragment

class ViewPager2Adapter(fa: FragmentActivity,) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return Constants.Num.NUM_PAGE
    }

    override fun createFragment(position: Int): Fragment = TabFragment()

}