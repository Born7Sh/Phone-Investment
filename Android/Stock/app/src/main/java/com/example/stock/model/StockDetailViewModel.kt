package com.example.stock.model

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stock.data.CandleStock
import com.example.stock.data.DataUtil
import com.example.stock.data.Stock
import com.github.mikephil.charting.data.CandleEntry

class StockDetailViewModel() : ViewModel() {
    private val _stockList = MutableLiveData<ArrayList<CandleEntry>>()
    val stockList: LiveData<ArrayList<CandleEntry>>
        get() = _stockList

    private var items = ArrayList<CandleEntry>()

    init {
        for (candleStock in DataUtil.getCandleStockData()) {
            items.add(
                CandleEntry(
                    candleStock.createdAt.toFloat(),
                    candleStock.shadowHigh,
                    candleStock.shadowLow,
                    candleStock.open,
                    candleStock.close
                )
            )
        }
        _stockList.value = items
    }

    fun btnSellClick(view : View){
        Log.v("items","파는거 입니다. 반갑습니다 허허허")
    }

    fun btnBuyClick(view : View){
        Log.v("items","사는거 입니다. 반갑습니다 허허허")
    }

}