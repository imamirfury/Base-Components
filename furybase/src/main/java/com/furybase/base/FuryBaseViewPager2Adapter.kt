package com.furybase.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/***
 * Created By Amir Fury on December 14 2021
 *
 * Email: Fury.amir93@gmail.com
 * */

class FuryBaseViewPager2Adapter : FragmentStateAdapter {

    // TODO: 14/12/21 Constructor For Fragment
    constructor(fragment: Fragment) : super(fragment)

    // TODO: 14/12/21 Constructor For Activity
    constructor(activity: FragmentActivity) : super(activity)

    private val fragments = arrayListOf<Fragment>()

    // TODO: 14/12/21 Submit An ArrayList Of Fragments Which You Wants In Your ViewPager
    fun submitFragments(list: ArrayList<Fragment>) {
        list.forEach {
            fragments.add(it)
            notifyItemInserted(fragments.size - 1)
        }
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}