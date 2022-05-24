package com.example.stock.model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {
    init {}

    fun btnSignupClick(view: View) {
        Toast.makeText(view.context, " ", Toast.LENGTH_SHORT).show()
    }
}