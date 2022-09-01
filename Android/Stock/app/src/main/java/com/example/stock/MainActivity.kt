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





    companion object {
        const val TAG = "MainActivityTag"
    }


}
