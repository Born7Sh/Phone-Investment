package com.example.stock.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stock.util.DataUtilLine
import com.github.mikephil.charting.data.*

class AnalysisViewModel : ViewModel() {

    private val _barDataEntryList = MutableLiveData<ArrayList<Entry>>()
    val barDataEntryList: LiveData<ArrayList<Entry>>
        get() =
            _barDataEntryList

    private var items = ArrayList<Entry>()
    private var average : Float = 0F

    init {
        for (stock in DataUtilLine.getBarData()) {
            average += stock.asset.toFloat()
        }
        average /= DataUtilLine.getBarData().size

        // 그래프에 들어갈 데이터 준비
        for (stock in DataUtilLine.getBarData()) {
            items.add(Entry(stock.createdAt.toFloat(), stock.asset.toFloat()))
        }
        _barDataEntryList.value = items
    }
    fun getAverage() : Float{
        return average
    }
}