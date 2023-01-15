package com.purnendu.timetable.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.purnendu.timetable.ui.viewPagerFragments.*


const val NUM_TABS = 7

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return Monday()
            1 -> return Tuesday()
            2 -> return Wednesday()
            3 -> return Thursday()
            4 -> return Friday()
            5 -> return Saturday()
        }
        return Sunday()
    }

}