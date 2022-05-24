package com.example.stock.model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){

    private val _id = MutableLiveData<String>()
    val id: LiveData<String>
        get() = _id

    private val _pwd = MutableLiveData<String>()
    val pwd: LiveData<String>
        get() = _pwd

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


    fun btnLoginClick(view : View) {
        Toast.makeText(view.context, "id : "+ id.value + "pwd : " + pwd.value, Toast.LENGTH_SHORT).show()
    }
}
