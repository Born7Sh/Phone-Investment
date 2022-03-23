package com.example.stock.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stock.data.Stock

class StockViewModel : ViewModel() {
    var liveData: MutableLiveData<ArrayList<Stock>> = MutableLiveData<ArrayList<Stock>>()

    init {
        var StockData = ArrayList<Stock>()
        StockData.add(Stock("first", "test1", "10000", "test"))
        StockData.add(Stock("second", "test2", "20000", "test"))
        StockData.add(Stock("third", "test3", "30000", "test"))
        liveData.postValue(StockData)
    }

}

