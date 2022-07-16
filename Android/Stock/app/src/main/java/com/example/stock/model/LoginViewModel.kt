package com.example.stock.model

import android.content.Context.MODE_PRIVATE
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.stock.R
import com.example.stock.data.Auth
import com.example.stock.data.SecureSharedPreferences
import com.example.stock.data.retrofit.GlobalApplication
import com.example.stock.data.retrofit.RetroAPI
import retrofit2.Call
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _id = MutableLiveData<String>()
    val id: LiveData<String>
        get() = _id

    private val _pwd = MutableLiveData<String>()
    val pwd: LiveData<String>
        get() = _pwd

    private val _successLogin = MutableLiveData<Boolean>(false)
    val successLogin: LiveData<Boolean>
        get() = _successLogin

    private val _errorText = MutableLiveData<String>()
    val errorText: LiveData<String>
        get() = _errorText

//    1. 저장되어 있는 아이디랑 비밀번호가 있는지 확인
//2. 없으면, 1)로 있으면 2)로
//
//1) 처음 로그인 할 때 / 일단
//
//2) 로그인 해서 키 / 아이디 / 비번 / sharedPrefernece있는 경우
//get 요청 하려면 : 키 + 쿼리 필요함
//1. 저장되어있는 아이디/비번으로 키받아와야함. (키업데이트)
//2. 키 가지고 / user, stock 가져오기
//
//로그인은 동기.
//stock 받아오는건 비동기.

    fun onIdTextChanged(s: CharSequence) {
        var b: String? = s.toString()

        if (b != "") {
            _id.value = b!!

        } else {
            _id.value = " "
        }

    }

    fun onPwdTextChanged(s: CharSequence) {
        var b: String? = s.toString()

        if (b != "") {
            _pwd.value = b!!

        } else {
            _pwd.value = " "
        }

    }


    fun btnLoginClick() {
        var auth = Auth(id.value!!, pwd.value!!)
        val call = GlobalApplication.baseService.create(RetroAPI::class.java)
            .getUserKey(GlobalApplication.auth)
        call.enqueue(object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.v("items", "HI response")
                if (response.isSuccessful) {
                    _successLogin.value = true
//                    var key = response.body()!!
//                    var sharedPrefs = getSharedPreferences("loginData", MODE_PRIVATE)
//                    val secureSharedPreferences = SecureSharedPreferences.wrap(sharedPrefs)
//                    secureSharedPreferences.put("key", key)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.v("items", "HI Failure")
            }

        })
    }

    fun btnSignup(view: View) {
        view.findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
    }
}
