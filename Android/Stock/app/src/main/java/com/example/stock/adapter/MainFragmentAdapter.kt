package com.example.stock.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.stock.fragment.AnalFragment
import com.example.stock.fragment.FavoriteFragment
import com.example.stock.fragment.HomeFragment
import com.example.stock.fragment.SettingFragment

class MainFragmentAdapter (fm : FragmentManager, val fragmentCount : Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return HomeFragment()
            1 -> return FavoriteFragment()
            2 -> return AnalFragment()
            3 -> return SettingFragment()
        }
        throw IllegalStateException("position $position is invalid for this viewpager")
    }

    override fun getCount(): Int = fragmentCount // 자바에서는 { return fragmentCount }

}