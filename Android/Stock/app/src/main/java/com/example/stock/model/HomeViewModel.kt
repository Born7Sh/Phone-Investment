package com.example.stock.model

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.stock.R
import com.example.stock.data.Event

class HomeViewModel() : ViewModel() {

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
//        view.findNavController().navigate(R.id.action_HomeFragment_to_loginFragment)
    }
}