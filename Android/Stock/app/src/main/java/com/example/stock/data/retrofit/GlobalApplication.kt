package com.example.stock.data.retrofit

import android.app.Application
import android.util.Log
import com.example.stock.data.AndroidKeyStoreUtil
import com.example.stock.data.SecureSharedPreferences
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

class GlobalApplication : Application() {
    companion object {
//        lateinit var prefs: SharedPreferences

        //Retrofit2
        lateinit var baseService: Retrofit
            private set



    }

    override fun onCreate() {
        super.onCreate()
//        prefs = MySharedPreferences(applicationContext)

        AndroidKeyStoreUtil.init(this)
        var sharedPrefs = getSharedPreferences("key", MODE_PRIVATE)
        val secureSharedPreferences = SecureSharedPreferences.wrap(sharedPrefs)
        val a = secureSharedPreferences.get("key", "")
        Log.v("items", "a = " + a)


        baseService = initRetrofitBuilder()
    }

    private fun initRetrofitBuilder(): Retrofit {
        // 기본 주소
        val BASE_URL = "http://localhost:8080/sleepapp/status/"


        //리턴하는 레트로핏 빌더 반환
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}