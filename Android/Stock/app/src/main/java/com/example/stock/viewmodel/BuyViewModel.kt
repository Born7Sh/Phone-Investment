package com.example.stock.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stock.data.model.Deal
import com.example.stock.data.model.Stock
import com.example.stock.data.repository.StockRepository
import com.example.stock.data.retrofit.handleApi
import com.example.stock.global.GlobalApplication
import com.example.stock.util.*
import kotlinx.coroutines.launch

class BuyViewModel(private val repository: StockRepository) : ViewModel() {

    private val _currentStock = MutableLiveData<Stock>()
    val currentStock: LiveData<Stock>
        get() = _currentStock

    private val _retroState = MutableLiveData<String>()
    val retroState: LiveData<String>
        get() = _retroState

    private val _backBtnClick = MutableLiveData<Event<Boolean>>()
    val backBtnClick: LiveData<Event<Boolean>>
        get() = _backBtnClick

    private val _buyBtnClick = MutableLiveData<Event<Boolean>>()
    val buyBtnClick: LiveData<Event<Boolean>>
        get() = _buyBtnClick

    // 22/09/05 필요한거는 3개임.
    // 몇개 살거라는 num
    // 주 하나당 가격 price
    // 총 가격
    private var stockNum: Int = 0

    private val _price = MutableLiveData<Float>()
    val price: LiveData<Float>
        get() = _price

    private val _totalPrice = MutableLiveData<Float>()
    val totalPrice: LiveData<Float>
        get() = _totalPrice

    private val _clickAble = MutableLiveData<Boolean>()
    val clickAble: LiveData<Boolean>
        get() = _clickAble

    // 22/09/05
    // buyViewModel 흐름
    // 처음에 fragment에서 updateStockPrice 통해서 데이터 초기화 (DB에서 데이터 가져옴)
    // 이거 토대로 1주 당 가격인 price 초기화
    // UI에서 주식 개수 만지면 onTextChanged() 호출 -> num/totalprice 저장.
    // UI에 totalprice 보여주기. observer 통해서 보여주기.

    init {
        _clickAble.postValue(false)
    }

    fun updateStockPrice() {
        viewModelScope.launch {
            repository.getCurrentStock(GlobalApplication.currentStock.symbol).let {
                _currentStock.postValue(it)
                GlobalApplication.currentStock = it
                _price.postValue(it.price)
            }
        }
    }

    fun retroRequestBuy() {
        viewModelScope.launch {
            var deal = Deal(
                GlobalApplication.currentStock.symbol,
                stockNum,
                GlobalApplication.currentStock.price,
                GlobalApplication.auth.username
            )

            val result: ApiResult<String> = handleApi({
                repository.buyRequest(
                    deal,
                    GlobalApplication.key
                )
            })
            when (result) {
                is Success -> {
                    _retroState.postValue("200")
                }
                is ApiError -> {
                    Log.d("items", "에러입니다. : " + result.exception)
                    _retroState.postValue("400")

                }
                is ExceptionError -> {
                    _retroState.postValue("400")
                }
            }
        }
    }


    fun btnCloseClick() {
        _backBtnClick.value = Event(true)
    }

    fun btnBuyClick() {
        _buyBtnClick.value = Event(true)
    }

    fun onTextChanged(s: CharSequence) {

        if (s.toString() != "") {
            stockNum = s.toString().toInt()
            var price = _price.value
            _clickAble.postValue(true)
            if (price != null) {
                _totalPrice.value = price * stockNum!!
            }
        } else {
            _clickAble.postValue(false)
            var price = _price.value
                _totalPrice.value = price!!
        }

    }


}