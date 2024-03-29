package com.example.stock.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    // 전체 회사 리스트
    private val _companyList = MutableLiveData<ArrayList<String>>()
    val companyList: LiveData<ArrayList<String>>
        get() = _companyList

    private var items = ArrayList<String>()

    init {
        items = arrayListOf(
            "AmerisourceBergen",
            "아처 대니얼스 미들랜드",
            "암드",
            "앤섬",
            "아메리칸 익스프레스",
        )
        _companyList.value = items

    }


}