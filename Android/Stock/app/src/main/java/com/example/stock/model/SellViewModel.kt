package com.example.stock.model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController

class SellViewModel :  ViewModel() {

    private val _price = MutableLiveData<Int>()
    val price: LiveData<Int>
        get() = _price

    private var per_stock: Int = 0


    fun btnCloseClick(view: View) {
        view.findNavController().navigateUp()
    }

    fun btnSellClick(view: View) {
        Toast.makeText(view.context, "매도 버튼을 누르셨군ㅇㅅ", Toast.LENGTH_SHORT).show()

    }

    fun onTextChanged(s: CharSequence) {
        var b: String? = s.toString()

        if (b != "") {
            var a: Int? = b?.toInt()
            _price.value = (per_stock * a!!)
        } else {
            _price.value = (per_stock * 1)
        }

    }

}