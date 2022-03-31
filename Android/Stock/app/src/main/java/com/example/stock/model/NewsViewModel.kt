package com.example.stock.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stock.data.News

class NewsViewModel() : ViewModel() {

    private val _newsList = MutableLiveData<ArrayList<News>>()
    val newsList: LiveData<ArrayList<News>>
        get() = _newsList

    private var items = ArrayList<News>()

    init {
        items = arrayListOf(
            News("1", "붕괴하는 한국 경제", "2022.11.14", "", ""),
            News("2", "붕괴하는 일본 경제", "2022.11.13", "", ""),
            News("3", "붕괴하는 미국 경제", "2022.11.12", "", ""),
            News("4", "붕괴하는 인도 경제", "2022.11.11", "", ""),
            News("5", "붕괴하는 중국 경제", "2022.11.10", "", ""),
            News("6", "붕괴하는 ㄴㄴ 경제", "2022.11.9", "", "")
        )
        _newsList.value = items
    }

}