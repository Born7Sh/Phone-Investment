package com.example.stock.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stock.R
import com.example.stock.data.News
import com.example.stock.data.Stock
import com.example.stock.data.StockOwn

class MainViewModel() : ViewModel() {

    // 전체 회사 리스트
    private val _stockList = MutableLiveData<ArrayList<Stock>>()
    val stockList: LiveData<ArrayList<Stock>>
        get() = _stockList

    // 내가 소유한 리스트
    private val _stockOwnList = MutableLiveData<ArrayList<StockOwn>>()
    val stockOwnList: LiveData<ArrayList<StockOwn>>
        get() = _stockOwnList

    // 내가 즐겨찾기 한 리스트
    private val _stockFavoriteList = MutableLiveData<ArrayList<Stock>>()
    val stockFavoriteList: LiveData<ArrayList<Stock>>
        get() = _stockFavoriteList

    // 뉴스 리스트
    private val _newsList = MutableLiveData<ArrayList<News>>()
    val newsList: LiveData<ArrayList<News>>
        get() = _newsList

    // 각자 초기화용도
    private var news = ArrayList<News>()
    private var stocks = ArrayList<Stock>()
    private var stockOwns = ArrayList<StockOwn>()

    init {
        stocks = arrayListOf(
            Stock("1", "AmerisourceBergen", "ABC", "", "", R.drawable.c_abc),
            Stock("2", "아처 대니얼스 미들랜드", "ADM", "", "", R.drawable.c_adm),
            Stock("3", "암드", "AMD", "", "", R.drawable.c_amd),
            Stock("4", "앤섬", "ANTM", "", "", R.drawable.c_antm),
            Stock("5", "아메리칸 익스프레스", "AXP", "", "", R.drawable.c_axp),
        )
        _stockList.value = stocks

        stocks = arrayListOf(
            Stock("2", "아처 대니얼스 미들랜드", "ADM", "", "", R.drawable.c_adm),
            Stock("3", "암드", "AMD", "", "", R.drawable.c_amd),
            Stock("4", "앤섬", "ANTM", "", "", R.drawable.c_antm),
        )
        _stockFavoriteList.value = stocks

        stockOwns = arrayListOf(
            StockOwn("1", "AmerisourceBergen", "ABC", "", "", "@drawable/c_abc"),
            StockOwn("5", "아메리칸 익스프레스", "AXP", "", "", "@drawable/c_axp"),
        )
        _stockOwnList.value = stockOwns

        news = arrayListOf(
            News("1", "붕괴하는 한국 경제", "2022.11.14", "", ""),
            News("2", "붕괴하는 일본 경제", "2022.11.13", "", ""),
            News("3", "붕괴하는 미국 경제", "2022.11.12", "", ""),
        )
        _newsList.value = news
    }

}