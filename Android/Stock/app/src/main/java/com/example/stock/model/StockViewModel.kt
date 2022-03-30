package com.example.stock.model

import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.*
import com.example.stock.data.Stock


class StockViewModel() : ViewModel() {

    private val _stockList = MutableLiveData<ArrayList<Stock>>()
    val stockList : LiveData<ArrayList<Stock>>
        get() = _stockList

    private var items = ArrayList<Stock>()

    init{
        items = arrayListOf(
            Stock("1","homin1","100,000",""),
            Stock("2","homin2","200,000",""),
            Stock("3","homin3","300,000",""),
            Stock("4","homin4","400,000",""),
            Stock("5","homin5","500,000",""),
            Stock("6","homin6","600,000","")
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

