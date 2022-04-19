package com.example.stock.model

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stock.data.Stock
import java.sql.Types.NULL

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

    }

    fun btnBuyClick(view: View) {

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