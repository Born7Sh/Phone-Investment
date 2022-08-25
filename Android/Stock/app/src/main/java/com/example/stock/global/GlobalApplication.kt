package com.example.stock.global

import android.app.Application
import android.content.SharedPreferences
import com.example.stock.BuildConfig
import com.example.stock.util.AndroidKeyStoreUtil
import com.example.stock.data.model.Auth
import com.example.stock.util.SecureSharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class GlobalApplication : Application() {
    companion object {
        lateinit var sharedPrefs: SharedPreferences

        // Retrofit2
        lateinit var baseService: Retrofit
            private set

        // 로그인 했냐?
        var haveLogin: Boolean = true
            private set

        // 키 반환용 변수
        lateinit var auth: Auth
            private set

        lateinit var key : String

        // 유저 돈
//        lateinit var money: Int = 0
    }

    override fun onCreate() {
        super.onCreate()
//        prefs = MySharedPreferences(applicationContext)
//        https://hyperconnect.github.io/2018/06/03/android-secure-sharedpref-howto.html

        AndroidKeyStoreUtil.init(this)
        sharedPrefs = getSharedPreferences("loginData", MODE_PRIVATE)
        val secureSharedPreferences = SecureSharedPreferences.wrap(sharedPrefs)

        secureSharedPreferences.put("id", "homin")
        secureSharedPreferences.put("pass", "1234")

        val sharedPreferenceId = secureSharedPreferences.get("id", "NULL")
        val sharedPreferencePw =  secureSharedPreferences.get("pass", "NULL")
//        key = secureSharedPreferences.get("key", "NULL")

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
//        val BASE_URL = "http://222.112.18.141:8080/"
        val BASE_URL = "https://9de12e98-1be1-47bd-90c2-c2a6f1b00cd3.mock.pstmn.io/"



        val gson : Gson = GsonBuilder()
            .setLenient()
            .create()


        // 로그용도
        val interceptor  = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG){
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        }else{
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        val client = OkHttpClient().newBuilder().addNetworkInterceptor(interceptor).build()

        //리턴하는 레트로핏 빌더 반환
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()


    }

}