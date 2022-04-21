package com.example.stock.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SellViewModelFactory (private val param: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SellViewModel::class.java)) {
            SellViewModel(param) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}