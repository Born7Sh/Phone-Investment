package com.example.stock.model

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController

class SellViewModel(stock: Int) :  ViewModel() {

    private val _price = MutableLiveData<Int>()
    val price: LiveData<Int>
        get() = _price

    private val _afterSell = MutableLiveData<Int>()
    val afterSell: LiveData<Int>
        get() = _afterSell

    private var per_stock: Int = 0

    init {
        per_stock = stock
        _price.value = per_stock
    }

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