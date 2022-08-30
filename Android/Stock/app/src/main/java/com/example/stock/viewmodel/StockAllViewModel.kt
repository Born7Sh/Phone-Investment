package com.example.stock.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stock.data.model.Stock
import com.example.stock.data.repository.StockRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StockAllViewModel(private val repository: StockRepository) : ViewModel() {
    private val _allStockList = MutableLiveData<List<Stock>>()
    val allStockList: LiveData<List<Stock>>
        get() = _allStockList

    fun updateAllStockList() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getAllStock().let {
                _allStockList.postValue(it)
            }
        }
    }
}