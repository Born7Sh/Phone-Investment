//package com.example.stock.adapter
//
//import androidx.fragment.app.FragmentManager
//import androidx.viewpager.widget.ViewPager
//import com.example.stock.views.AnalFragment
//import com.example.stock.views.FavoriteFragment
//import com.example.stock.views.HomeFragment
//import com.example.stock.views.SettingFragment
//
//class MainViewModel(val contract: MainActivityContract) {
//    interface MainActivityContract {
//        fun getFragmentManger(): FragmentManager
//    }
//
//    var adapter = PagerAdapter(
//        contract.getFragmentManger(),
//        listOf("Tab1", "Tab2", "Tab3", "Tab4"),
//        listOf(
//            HomeFragment.newInstance("1"),
//            FavoriteFragment.newInstance("2"),
//            AnalFragment.newInstance("3"),
//            SettingFragment.newInstance("4")
//        )
//    )
//
//
//    var currentPosition = 0
//
//    var pageChangeListener = object : ViewPager.OnPageChangeListener {
//
//        override fun onPageScrollStateChanged(p0: Int) {
//        }
//
//        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
//        }
//
//        override fun onPageSelected(p0: Int) {
//            currentPosition = p0
//        }
//    }
//
//}