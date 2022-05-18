package com.example.stock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels

import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.stock.databinding.ActivityMainBinding
import com.example.stock.model.MainViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initNavigation()
    }

    private fun initNavigation() {
        val navController = findNavController(R.id.main_nav_host)
        binding.mainBottomNavigation.setupWithNavController(navController)

                // bottomNavigation 한거 안보이게
                navController.addOnDestinationChangedListener { _, destination, _ ->
                    if (destination.id == R.id.stockDetailFragment ||
                        destination.id == R.id.buyFragment ||
                destination.id == R.id.sellFragment
            ) {
                binding.mainBottomNavigation.visibility = View.GONE
            } else {
                binding.mainBottomNavigation.visibility = View.VISIBLE
            }


        }
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }

    companion object {
        const val TAG = "MainActivityTag"
    }


}
