package com.example.stock.views.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.stock.R
import com.example.stock.adapter.StockAdapter
import com.example.stock.databinding.FragmentHomeStockBinding
import com.example.stock.model.StockViewModel


class HomeStockFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding : FragmentHomeStockBinding
    private lateinit var stockViewModel: StockViewModel
    private lateinit var stockAdapter: StockAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_stock, container, false)
        stockAdapter = StockAdapter()
        binding.recycler.adapter = stockAdapter
        return binding.root
        return inflater.inflate(R.layout.fragment_home_stock, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stockViewModel = ViewModelProvider(this).get(StockViewModel::class.java)
        binding.viewModel = stockViewModel

        stockViewModel.stockList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            stockAdapter.setData(it)
        })


    }


}