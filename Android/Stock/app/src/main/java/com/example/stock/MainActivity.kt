package com.example.stock

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog

import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.stock.databinding.ActivityMainBinding
import com.example.stock.model.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val mainViewModel by viewModels<MainViewModel>()
    private lateinit var appBarConfiguration: AppBarConfiguration



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
                        destination.id == R.id.sellFragment ||
                        destination.id == R.id.loginFragment ||
                        destination.id == R.id.signupFragment
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

//    override fun onBackPressed() {
//        when(NavHostFragment.findNavController(main_nav_host).currentDestination?.id) {
//            R.id.HomeFragment-> {
//                val dialog= AlertDialog.Builder(this).setMessage("Hello").setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->
//                    finish()
//                }).show()
//            }
//            else -> {
//                super.onBackPressed()
//            }
//        }
//    }

    companion object {
        const val TAG = "MainActivityTag"
    }




}
