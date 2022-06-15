package com.example.stock.model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController

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


    init {}

    fun btnSignupClick(view: View) {
        Toast.makeText(view.context, "id : " + id.value + " pwd : " + pwd.value + "가입을 축하드려요", Toast.LENGTH_SHORT)
            .show()
        view.findNavController().navigateUp()
    }

}