package com.example.stock.model

import android.util.Log
import androidx.lifecycle.*
import com.example.stock.data.Stock


class StockViewModel() : ViewModel() {

    private val _stockList = MutableLiveData<ArrayList<Stock>>()
    val stockList : LiveData<ArrayList<Stock>>
        get() = _stockList

    private var items = ArrayList<Stock>()

    init{
        items = arrayListOf(
            Stock("1","homin","100000",""),
        )
        _stockList.value = items
    }

    fun buttonClick(){
        val range = (1000..10000)
        val user = Stock("1","homin",range.random().toString(),"")
        items.add(user)

        Log.v("변하는 List", "크기는 몇이여? " + items.size)
        for (i in items){
            Log.v("tag1", i.name +" "+ i.price)
        }
        _stockList.postValue(items)

        for (i in stockList.value!!){
            Log.v("안변하는 List", i.name +" "+ i.price)
        }

    }
}

