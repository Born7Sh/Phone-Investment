package com.example.stock.data.retrofit

import android.app.Application
import android.util.Log
import com.example.stock.data.AndroidKeyStoreUtil
import com.example.stock.data.Auth
import com.example.stock.data.SecureSharedPreferences
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

class GlobalApplication : Application() {
    companion object {
//        lateinit var prefs: SharedPreferences

        // Retrofit2
        lateinit var baseService: Retrofit
            private set

        // 키 반환용 변수
        lateinit var auth: Auth
            private set

        // 로그인 했냐?
        var haveLogin: Boolean = true
            private set


    }

    override fun onCreate() {
        super.onCreate()
//        prefs = MySharedPreferences(applicationContext)
//        https://hyperconnect.github.io/2018/06/03/android-secure-sharedpref-howto.html

        AndroidKeyStoreUtil.init(this)
        var sharedPrefs = getSharedPreferences("loginData", MODE_PRIVATE)
        val secureSharedPreferences = SecureSharedPreferences.wrap(sharedPrefs)

        secureSharedPreferences.put("id", "HoMinXio")
        secureSharedPreferences.put("pass", "1234")

        val sharedPreferenceId = secureSharedPreferences.get("id", "NULL")
        val sharedPreferencePw =  secureSharedPreferences.get("pass", "NULL")
//        val c = secureSharedPreferences.get("key2", "NULL")

//        Log.v("items", "id = " + auth.username)
//        Log.v("items", "pass = " + auth.username)
//        Log.v("items", "key = " + c)

        if (sharedPreferenceId == "NULL" && sharedPreferencePw == "NULL") {
            haveLogin = false
        }else{
            auth = Auth(sharedPreferenceId,sharedPreferencePw)
        }

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