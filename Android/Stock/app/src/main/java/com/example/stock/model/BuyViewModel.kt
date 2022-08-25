package com.example.stock.model

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController

class BuyViewModel(stock: Int) : ViewModel() {

    private val _price = MutableLiveData<Int>()
    val price: LiveData<Int>
        get() = _price

    private var per_stock: Int = 0

//    val inputText = MutableLiveData<String>()

    init {
        per_stock = stock
        _price.value = per_stock
    }

    fun btnCloseClick(view: View) {
        view.findNavController().navigateUp()
    }

    fun btnBuyClick(view: View) {
        Toast.makeText(view.context, "매수 버튼을 누르셨군ㅇㅅ", Toast.LENGTH_SHORT).show()

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


    fun onEditTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
            }
        }
    }

}