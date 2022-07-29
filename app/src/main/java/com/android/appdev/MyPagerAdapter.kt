package com.android.appdev

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MyPagerAdapter (fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    //position위치의 프레그먼트
    private val items = ArrayList<Fragment>()

    override fun getItem(position: Int): Fragment {
        return items[position]
    }

    //프레그먼트의 갯수
    override fun getCount(): Int {
        return items.size
    }

    fun updateFragments(items:List<Fragment>){
        this.items.addAll(items)
    }
}