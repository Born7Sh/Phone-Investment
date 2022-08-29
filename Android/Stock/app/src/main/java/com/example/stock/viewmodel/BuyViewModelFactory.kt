package com.example.stock.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BuyViewModelFactory (private val param: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BuyViewModel::class.java)) {
            BuyViewModel(param) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}