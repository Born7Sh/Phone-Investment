package com.example.stock.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stock.data.model.Stock
import com.example.stock.data.repository.StockRepository
import com.example.stock.global.GlobalApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: StockRepository) : ViewModel() {
    private val _favoriteStockList = MutableLiveData<List<Stock>>()
    val favoriteStockList: LiveData<List<Stock>>
        get() = _favoriteStockList

    lateinit var stockList : List<Stock>

    fun updateFavoriteStockList() {
        viewModelScope.launch {
            repository.getFavoriteStock().let {
                stockList = it
                _favoriteStockList.postValue(it)
            }
        }
    }

}