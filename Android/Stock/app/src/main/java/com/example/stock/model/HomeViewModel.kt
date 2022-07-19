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

    fun search() {
        _searchBtnClick.value = Event(true)
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