package com.example.stock.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stock.data.model.Auth
import com.example.stock.data.model.Stock
import com.example.stock.data.repository.StockRepository
import com.example.stock.data.retrofit.handleApi
import com.example.stock.global.GlobalApplication
import com.example.stock.util.*
import kotlinx.coroutines.*
import java.net.ConnectException

class HomeViewModel(private val repository: StockRepository) : ViewModel() {
    // 버튼 클릭 변수
    private val _newsBtnClick = MutableLiveData<Event<Boolean>>()
    val newsBtnClick: LiveData<Event<Boolean>>
        get() = _newsBtnClick

    private val _rankBtnClick = MutableLiveData<Event<Boolean>>()
    val rankBtnClick: LiveData<Event<Boolean>>
        get() = _rankBtnClick

    private val _searchBtnClick = MutableLiveData<Event<Boolean>>()
    val searchBtnClick: LiveData<Event<Boolean>>
        get() = _searchBtnClick

    private val _newsGoBtnClick = MutableLiveData<Event<Boolean>>()
    val newsGoBtnClick: LiveData<Event<Boolean>>
        get() = _newsGoBtnClick

    private val _rankGoBtnClick = MutableLiveData<Event<Boolean>>()
    val rankGoBtnClick: LiveData<Event<Boolean>>
        get() = _rankGoBtnClick

    private val _myStockGoBtnClick = MutableLiveData<Event<Boolean>>()
    val myStockGoBtnClick: LiveData<Event<Boolean>>
        get() = _myStockGoBtnClick

    private val _isMainBtnClick = MutableLiveData<Event<Boolean>>()
    val isMainBtnClick: LiveData<Event<Boolean>>
        get() = _isMainBtnClick

    // Retro용 변수
    private val _myStockList = MutableLiveData<List<Stock>>()
    val myStockList: LiveData<List<Stock>>
        get() = _myStockList

    private val _IMSI = MutableLiveData<Event<Boolean>>()
    val IMSI: LiveData<Event<Boolean>>
        get() = _IMSI

    fun search() {
        _searchBtnClick.value = Event(true)
    }

    fun myStockGoClick() {
        _myStockGoBtnClick.value = Event(true)
    }

    fun rankGoClick() {
        _rankGoBtnClick.value = Event(true)
    }

    fun newsGoClick() {
        _newsGoBtnClick.value = Event(true)
    }

    fun mainBtnClick() {
        _isMainBtnClick.value = Event(true)
    }

    fun newsClick() {
        _newsBtnClick.value = Event(true)
    }

    fun rankClick() {
        _rankBtnClick.value = Event(true)
    }

    fun IMSI() {
        _IMSI.value = Event(true)
    }


    fun updateMyStockList() {
        viewModelScope.launch {
            repository.getOwnStock().let {
                _myStockList.postValue(it)
            }


        }

    }

    fun dataCoroutineFun(auth: Auth) {
        viewModelScope.launch {
            Log.d("items", "dataCoroutineFun 1) 입장. : ")
            val test: ApiResult<Float> = handleApi({
                repository.getMyMoney(GlobalApplication.auth.username, GlobalApplication.key)
            })
            Log.d("items", "dataCoroutineFun 2) data return")
            when (test) {
                is Success -> {
                    Log.d("items", "dataCoroutineFun 2-1) tocken 최신임")
                    dataLoading(auth) // 모든게 정상적인 경우
                }
                is ApiError -> {
                    Log.d("items", "dataCoroutineFun 2-2) tocken 옛날거임")
                    Log.d("items", "에러입니다. : " + test.exception)
                    tokenUpdate(auth)
                    dataLoading(auth)
                }
                is ExceptionError -> {
                    Log.d("items", "dataCoroutineFun 2-3) 에러임")
                }

            }

        }
    }

    private suspend fun tokenUpdate(auth: Auth) {
        // 토큰 받아오기
        Log.d("items", "dataCoroutineFun 2-2-1) tokenUpdate 진입")
        val result: ApiResult<String> = handleApi({
            repository.getUserKey(
                GlobalApplication.auth
            )
        })
        Log.d("items", "dataCoroutineFun 2-2-2) tokenUpdate 결과")
        when (result) {
            is Success -> {
                Log.d("items", "dataCoroutineFun 2-2-3) tokenUpdate 성공")
                GlobalApplication.key = result.data
            }
            is ApiError -> {
                Log.d("items", "dataCoroutineFun 2-2-3) tokenUpdate 실패")
                GlobalApplication.key = "444"
                // result.exception will provide the error
                Log.d("items", "에러입니다. : " + result.exception)
            }
        }


    }

    private fun dataLoading(auth: Auth) {
        // 본격적으로 데이터 받아오기.

        viewModelScope.launch {
            Log.d("items", "\"dataLoading-result1 함수 집입")
            val result1: ApiResult<Stock> = handleApi({
                repository.getMyStockList(
                    GlobalApplication.auth.username,
                    GlobalApplication.key
                )
            })
            Log.d("items", "dataLoading-result1 결과 나옴")

            when (result1) {
                is Success -> {
                    Log.d("items", "dataLoading-result1 성공")
                }
                is ApiError -> {
                    // result.exception will provide the error
                    Log.d("items", "dataLoading-result1 실패 성공")
                    Log.d("items", "에러입니다. : " + result1.exception)
                }
            }
        }

        viewModelScope.launch {
            Log.d("items", "\"dataLoading-result2 함수 집입")
            val result2: ApiResult<Float> = handleApi({
                repository.getMyMoney(
                    GlobalApplication.auth.username,
                    GlobalApplication.key
                )
            })
            Log.d("items", "dataLoading-result2 결과 나옴")
            when (result2) {
                is Success -> {
                    Log.d("items", "dataLoading-result2 성공")
                }
                is ApiError -> {
                    // result.exception will provide the error
                    Log.d("items", "dataLoading-result2 실패 성공")
                    Log.d("items", "에러입니다. : " + result2.exception)
                }
            }
        }


    }

}



