package com.example.stock.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stock.data.retrofit.RetroAPIRepository

class HomeViewModelFactory(private val repository: RetroAPIRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RetroAPIRepository::class.java).newInstance(repository)
    }
}