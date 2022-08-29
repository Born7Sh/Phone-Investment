package com.example.stock.viewmodel

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController


class CommunityViewModel : ViewModel() {

    private val _content = MutableLiveData<String>()
    val content: LiveData<String>
        get() = _content

    init {}

    fun btnBackClick(view: View) {
        view.findNavController().navigateUp()
    }

    fun btnWriteClick(view: View) {
        Toast.makeText(view.context, _content.value, Toast.LENGTH_SHORT).show()
    }

    fun onTextChanged(s: CharSequence) {
        var b: String? = s.toString()

        if (b != "") {
            _content.value = b!!

        } else {
            _content.value = " "
        }

    }

}