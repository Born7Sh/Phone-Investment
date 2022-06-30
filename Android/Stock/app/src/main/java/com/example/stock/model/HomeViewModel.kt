package com.example.stock.model

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.stock.R

class HomeViewModel() : ViewModel()  {


    fun newsClick(view: View) {
        view.findNavController().navigate(R.id.action_HomeFragment_to_newsFragment)
    }

    fun rankClick(view: View) {
        view.findNavController().navigate(R.id.action_HomeFragment_to_rankFragment)
    }


    fun search(view: View) {
        view.findNavController().navigate(R.id.action_HomeFragment_to_searchFragment)
    }


    fun IMSI(view: View) {
        view.findNavController().navigate(R.id.action_HomeFragment_to_loginFragment)
    }
}