package com.example.stock.model

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.stock.data.Auth
import com.example.stock.data.Event
import com.example.stock.data.SecureSharedPreferences
import com.example.stock.data.retrofit.GlobalApplication
import com.example.stock.data.retrofit.RetroAPI
import retrofit2.Call
import retrofit2.Response

class SignupViewModel : ViewModel() {

    private val _id = MutableLiveData<String>()
    val id: LiveData<String>
        get() = _id

    private val _pwd = MutableLiveData<String>()
    val pwd: LiveData<String>
        get() = _pwd

    private val _pwdCk = MutableLiveData<String>()
    val pwdCk: LiveData<String>
        get() = _pwdCk

    private val _birth = MutableLiveData<String>()
    val birth: LiveData<String>
        get() = _birth

    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name


    private val _loginBtnText = MutableLiveData<Event<String>>()
    val loginBtnText: LiveData<Event<String>>
        get() = _loginBtnText


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

    fun onPwdCheckTextChanged(s: CharSequence) {
        var b: String? = s.toString()

        if (b != "") {
            _pwdCk.value = b!!

        } else {
            _pwdCk.value = " "
        }

    }

    fun onNameTextChanged(s: CharSequence) {
        var b: String? = s.toString()

        if (b != "") {
            _name.value = b!!

        } else {
            _name.value = " "
        }

    }

    fun onBirthTextChanged(s: CharSequence) {
        var b: String? = s.toString()

        if (b != "") {
            _birth.value = b!!

        } else {
            _birth.value = " "
        }

    }


    fun btnSignupClick() {

        if (pwd.value ?: "ho" == pwdCk.value ?: "ho") {
            var auth = Auth(id.value ?: "ho", pwd.value ?: "go")
            val call = GlobalApplication.baseService.create(RetroAPI::class.java)
                .getUserKey(auth)
            call.enqueue(object : retrofit2.Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.v("items", "HI response")
                    if (response.isSuccessful) {
                        _loginBtnText.value = Event("200")
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.v("items", "로그인 실패")
                    _loginBtnText.value = Event("100")
                }

            })
        } else {
            _loginBtnText.value = Event("1")
        }


    }

}