package com.example.stock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.stock.databinding.ActivityMainBinding
import com.example.stock.viewmodel.MainViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.example.stock.global.GlobalApplication
import com.example.stock.data.repository.StockRepository
import com.example.stock.viewmodel.RepositoryViewModelFactory
import java.io.File


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var mainViewModel: MainViewModel
    private lateinit var repositoryViewModelFactory: RepositoryViewModelFactory


    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        initNavigation()
        initViewModel()
        getInitData()
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
    private fun initViewModel(){
        repositoryViewModelFactory = RepositoryViewModelFactory(StockRepository())
        mainViewModel = ViewModelProvider(this,repositoryViewModelFactory).get(MainViewModel::class.java)

        val DB_PATH = "/data/data/" + getPackageName()
        val DB_NAME = "stock.db"
        val DB_FULLPATH = "$DB_PATH/databases/$DB_NAME"

        val dbFile = File(DB_FULLPATH)
        if (dbFile.delete()) {
            println("삭제 성공")
        } else {
            println("삭제 실패")
        }

    }

    private fun getInitData(){
        // 데이터 받는 경우 3가지 경우
        // 1. 첫 로그인 한 경우 해서 토큰이 없는 경우 (initDataLoading)
        // key 값 받아오는 함수 호출
        //2. 기존의 key 값이 있는 경우 (dataLoading)
        //3. 기존의 key 값이 만료된 경우 (dataLoading -> initDataLoading 호출)
        // 에러 코드 확인 한 후 key 값 받아오는 함수 호출
        mainViewModel.dataCoroutineFun(GlobalApplication.auth)
    }

    private fun getUserKey() {

//        val call = GlobalApplication.baseService.create(RetroAPI::class.java)
//            .getUserKey(GlobalApplication.auth)
//        call.enqueue(object : retrofit2.Callback<String> {
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                if (response.isSuccessful) {
//                    var key = response.body()!!
//                    GlobalApplication.key = "Bearer $key"
//
//                    Log.v("items", "key is : " + key)
//                    var sharedPrefs = getSharedPreferences("loginData", MODE_PRIVATE)
//                    val secureSharedPreferences = SecureSharedPreferences.wrap(sharedPrefs)
//                    secureSharedPreferences.put("key", key)
//
//                }
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Log.v("items", " authentic Failure : "+ t)
//            }
//
//        })

    }

    companion object {
        const val TAG = "MainActivityTag"
    }


}
