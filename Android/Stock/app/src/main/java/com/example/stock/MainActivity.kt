package com.example.stock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels

import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.stock.data.SecureSharedPreferences
import com.example.stock.databinding.ActivityMainBinding
import com.example.stock.model.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import android.app.Activity

import android.content.SharedPreferences
import android.util.Log
import androidx.navigation.NavController
import com.example.stock.data.AndroidKeyStoreUtil
import com.example.stock.data.Stock
import com.example.stock.data.retrofit.GlobalApplication
import com.example.stock.data.retrofit.RetroAPI
import retrofit2.Call
import retrofit2.Response
import retrofit2.create


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    val mainViewModel by viewModels<MainViewModel>()

    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initNavigation()
    }


    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }

    private fun initNavigation() {
        navController = findNavController(R.id.main_nav_host)
        binding.mainBottomNavigation.setupWithNavController(navController)

        // https://velog.io/@nagosooo/Jetpack-navigation-startDestination-%EB%8F%99%EC%A0%81%EC%9C%BC%EB%A1%9C-%EC%84%A4%EC%A0%95
        // 로그인 화면 / 비로그인 화면 나누는 방법
        val navGraph =
            navController.navInflater.inflate(R.navigation.bottom_nav_graph) // app:navGraph="@navigation/nav_graph" 로 설정했던 것

        if (!GlobalApplication.haveLogin) navGraph.startDestination =
            R.id.loginFragment //setStartDestination 설정
        else {
            navGraph.startDestination = R.id.HomeFragment
            getUserKey()
        }

        navController.setGraph(navGraph, null) //navController에 graph 설정

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

    private fun getUserKey() {

        val call = GlobalApplication.baseService.create(RetroAPI::class.java)
            .getUserKey(GlobalApplication.auth)
        call.enqueue(object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    var key = response.body()!!
                    GlobalApplication.key = "Bearer $key"

                    Log.v("items", "key is : " + key)
                    var sharedPrefs = getSharedPreferences("loginData", MODE_PRIVATE)
                    val secureSharedPreferences = SecureSharedPreferences.wrap(sharedPrefs)
                    secureSharedPreferences.put("key", key)

                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.v("items", " authentic Failure : "+ t)
            }

        })

    }

    companion object {
        const val TAG = "MainActivityTag"
    }


}
