package com.example.stock.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stock.util.Event

class LoginViewModel : ViewModel() {

    private val _id = MutableLiveData<String>()
    val id: LiveData<String>
        get() = _id

    private val _pwd = MutableLiveData<String>()
    val pwd: LiveData<String>
        get() = _pwd

    // 버튼 클릭했을 때
    // 1. viewmodel 함수로 들어감
    // 2. 함수로 들어가서 값 준비 (성공/실패)
    // 3. 값을 Event 타입으로 넣어서 값 변경
    // 4. 값 변경 됐으니깐, observer 탐지해서 바뀐 값 it으로 날라감.
    private val _loginBtnClick = MutableLiveData<Event<String>>()
    val loginBtnClick: LiveData<Event<String>>
        get() = _loginBtnClick

    private val _signUpBtnClick = MutableLiveData<Event<Boolean>>()
    val signUpBtnClick: LiveData<Event<Boolean>>
        get() = _signUpBtnClick


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
        // 22/08/16 비동기 통신 처리를 위한 잠시 주석 처리
//        var auth = Auth(id.value ?: "ho", pwd.value ?: "go")
//        val call = GlobalApplication.baseService.create(RetroAPI::class.java)
//            .getUserKey(auth)
//        call.enqueue(object : retrofit2.Callback<String> {
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                Log.v("items", "HI response")
//                if (response.isSuccessful) {
//
//                    var key = response.body()!!
//
//                    val secureSharedPreferences =
//                        SecureSharedPreferences.wrap(GlobalApplication.sharedPrefs)
//                    secureSharedPreferences.put("key", key)
//
//                    _loginBtnClick.value = Event("200")
//                }
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Log.v("items", "로그인 실패")
//                _loginBtnClick.value = Event("100")
//            }
//
//        })

    }

    fun btnSignup() {
        _signUpBtnClick.value = Event(true)
    }
}
