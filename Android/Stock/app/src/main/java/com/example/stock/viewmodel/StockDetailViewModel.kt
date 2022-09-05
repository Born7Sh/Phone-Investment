package com.example.stock.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stock.data.Community
import com.example.stock.data.model.Stock
import com.example.stock.data.repository.StockRepository
import com.example.stock.global.GlobalApplication
import com.example.stock.util.DataUtilBar
import com.example.stock.util.Event
import com.github.mikephil.charting.data.CandleEntry
import kotlinx.coroutines.*
import java.text.NumberFormat

class StockDetailViewModel(private val repository: StockRepository) : ViewModel() {

    private val _stockList = MutableLiveData<ArrayList<CandleEntry>>()
    val stockList: LiveData<ArrayList<CandleEntry>>
        get() = _stockList

    // 22/08/30 얘가 favorite 안에 포함되는지 확인하기 위해 만든 변수
    private val _isFavorite = MutableLiveData<Int>()
    val isFavorite: LiveData<Int>
        get() = _isFavorite

    private var items = ArrayList<CandleEntry>()

    private var _stock = MutableLiveData<Stock>()
    val stock: LiveData<Stock>
        get() = _stock

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
        getDdData()

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

    private fun getDdData() {
        // 데이터 가져와서 favorite 초기설정
        CoroutineScope(Dispatchers.IO).launch {
            repository.getFavoriteStock().let {
                for (stock in it) {
                    if (stock.symbol == GlobalApplication.currentStock.symbol) {
                        _isFavorite.postValue(1)
                    }
                }
            }
        }
    }

    fun updatePrice() {
        Log.d("items", "price 함수 들어옴")
        viewModelScope.launch {
            Log.d("items", "price 등장")
            repository.getCurrentStock(GlobalApplication.currentStock.symbol).let {
                _stock.postValue(it)
                cancel()
            }
        }
    }

    fun btnHeartClick() {
        if (_isFavorite.value == 1) {
            favoriteTurnOff()
        } else {
            favoriteTurnOn()
        }
    }

    private fun favoriteTurnOn() {
        _isFavorite.value = 1
        viewModelScope.launch {
            Log.d("items", "favoriteTurnOn 들어옴")
            repository.modifyClassification(GlobalApplication.currentStock.symbol, 2).let {
            }
        }
    }

    private fun favoriteTurnOff() {
        _isFavorite.value = 0
        viewModelScope.launch {
            Log.d("items", "favoriteTurnOFF 들어옴")
            repository.modifyClassification(GlobalApplication.currentStock.symbol, 0).let {
            }
        }
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