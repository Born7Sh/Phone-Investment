package com.example.stock.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stock.data.Community
import com.example.stock.util.DataUtilBar
import com.example.stock.util.Event
import com.github.mikephil.charting.data.CandleEntry
import java.text.NumberFormat

class StockDetailViewModel() : ViewModel() {

    private val _stockList = MutableLiveData<ArrayList<CandleEntry>>()
    val stockList: LiveData<ArrayList<CandleEntry>>
        get() = _stockList

    private val _isFavorite = MutableLiveData<Int>()
    val isFavorite: LiveData<Int>
        get() = _isFavorite

    private var items = ArrayList<CandleEntry>()

    // 회사 리스트
    private val _communityList = MutableLiveData<ArrayList<Community>>()
    val communityList: LiveData<ArrayList<Community>>
        get() = _communityList

    private var community = ArrayList<Community>()

    private var _btnBackClick = MutableLiveData<Event<Boolean>>()
    val btnBackClick: LiveData<Event<Boolean>>
        get() = _btnBackClick

    private var _btnBuyClick = MutableLiveData<Event<Boolean>>()
    val btnBuyClick: LiveData<Event<Boolean>>
        get() = _btnBuyClick

    private var _btnSellClick = MutableLiveData<Event<Boolean>>()
    val btnSellClick: LiveData<Event<Boolean>>
        get() = _btnSellClick

    private var _btnCommunityClick = MutableLiveData<Event<Array<Community>>>()
    val btnCommunityClick: LiveData<Event<Array<Community>>>
        get() = _btnCommunityClick

    init {

        btnGraphNum1Click()

        community = arrayListOf(

            Community("김규동", "Apple", "AAPL", "드자가"),
            Community("김차동", "AmerisourceBergen", "ABC", "자 드가자"),
        )
        _communityList.value = community
    }

    fun setUnit(value: Int): String {
        return when {
            value >= 1E9 -> "${(value.toFloat() / 1E9).toInt()}B"
            value >= 1E6 -> "${(value.toFloat() / 1E6).toInt()}M"
            value >= 1E3 -> "${(value.toFloat() / 1E3).toInt()}K"
            else -> NumberFormat.getInstance().format(value)
        }

    }


    fun btnHeartClick() {
        if (_isFavorite.value == 1) {
            favoriteTurnOff()
        } else {
            favoriteTurnOn()
        }
    }

    fun favoriteTurnOn() {
        _isFavorite.value = 1
    }

    private fun favoriteTurnOff() {
        _isFavorite.value = 0
    }

    fun btnBackClick() {
        _btnBackClick.value = Event(true)
    }

    fun btnSellClick() {
        _btnSellClick.value = Event(true)
    }

    fun btnBuyClick() {
        _btnBuyClick.value = Event(true)
    }

    fun btnCommunity() {
        _btnCommunityClick.value = Event(community.toArray(arrayOfNulls<Community>(community.size)))

    }

    fun btnGraphNum1Click() {
        graphDataSet(3)
    }

    fun btnGraphNum2Click() {
        graphDataSet(5)
    }

    fun btnGraphNum3Click() {
        graphDataSet(7)
    }

    fun btnGraphNum4Click() {
        graphDataSet(9)
    }

    fun btnGraphNum5Click() {
        graphDataSet(100)
    }

    private fun graphDataSet(num: Int) {
        items.clear()
        var i = 0
        for (candleStock in DataUtilBar.getCandleStockData()) {
            if (i == num) {
                break
            }
            items.add(
                CandleEntry(
                    candleStock.createdAt.toFloat(),
                    candleStock.shadowHigh,
                    candleStock.shadowLow,
                    candleStock.open,
                    candleStock.close
                )
            )
            i += 1
        }
        _stockList.value = items
    }

}