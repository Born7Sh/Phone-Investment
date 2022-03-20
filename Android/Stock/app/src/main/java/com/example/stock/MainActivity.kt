package com.example.stock

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import com.example.stock.adapter.MainFragmentAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.R

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.example.stock.adapter.MainViewModel
import com.example.stock.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val viewModel = createVm()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main).setVariable(BR.viewModel,viewModel)
    }

    private fun createVm() = MainViewModel(object : MainViewModel.MainActivityContract {
        override fun getFragmentManger(): FragmentManager = supportFragmentManager
    })

//    private fun configureBottomNavigation() {
//
//        main_frag_pager.adapter = MainFragmentAdapter(supportFragmentManager, 4)
//        main_bottom_menu.setupWithViewPager(main_frag_pager)
//
//        val bottomNaviLayout: View =
//            this.layoutInflater.inflate(R.layout.bottom_navigation_tab, null, false)
//
//        main_bottom_menu.getTabAt(0)!!.customView =
//            bottomNaviLayout.findViewById(R.id.btn_bottom_home_tab) as RelativeLayout
//        main_bottom_menu.getTabAt(1)!!.customView =
//            bottomNaviLayout.findViewById(R.id.btn_bottom_favorite_tab) as RelativeLayout
//        main_bottom_menu.getTabAt(2)!!.customView =
//            bottomNaviLayout.findViewById(R.id.btn_bottom_anal_tab) as RelativeLayout
//        main_bottom_menu.getTabAt(3)!!.customView =
//            bottomNaviLayout.findViewById(R.id.btn_bottom_setting_tab) as RelativeLayout
//
//    }
}