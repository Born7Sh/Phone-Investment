package com.example.stock.views.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.stock.R
import com.example.stock.adapter.StockAdapter
import com.example.stock.databinding.FragmentFavoriteBinding
import com.example.stock.model.StockViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var stockViewModel: StockViewModel
    private lateinit var stockAdapter: StockAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        stockAdapter = StockAdapter()
        binding.recycler.adapter = stockAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stockViewModel = ViewModelProvider(this).get(StockViewModel::class.java)
        binding.viewModel = stockViewModel

        stockViewModel.stockList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            stockAdapter.setData(it)
        })


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataAdd()
    }


    fun dataAdd() {
        GlobalScope.launch {
            delay(15000L)
            stockViewModel.buttonClick()}
        println ("Hello,")
        Thread.sleep (2000L)
    }
}