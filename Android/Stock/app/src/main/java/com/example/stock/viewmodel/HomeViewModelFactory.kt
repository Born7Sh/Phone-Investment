package com.example.stock.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stock.data.repository.StockRepository

class HomeViewModelFactory(private val repository: StockRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(StockRepository::class.java).newInstance(repository)
    }
}