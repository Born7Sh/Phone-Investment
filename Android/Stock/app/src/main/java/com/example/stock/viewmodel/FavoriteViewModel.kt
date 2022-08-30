package com.example.stock.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stock.data.model.Stock
import com.example.stock.data.repository.StockRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: StockRepository) : ViewModel() {
    private val _favoriteStockList = MutableLiveData<List<Stock>>()
    val favoriteStockList: LiveData<List<Stock>>
        get() = _favoriteStockList

    fun updateMyStockList() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getFavoriteStock().let {
                _favoriteStockList.postValue(it)
            }
        }
    }
}