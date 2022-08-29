package com.example.stock.views.stockAll

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.stock.R
import com.example.stock.adapter.StockAdapter
import com.example.stock.data.model.Stock
import com.example.stock.databinding.FragmentStockAllBinding
import com.example.stock.viewmodel.MainViewModel

class StockAllFragment : Fragment() {
    lateinit var binding: FragmentStockAllBinding
    private val mainViewModel by activityViewModels<MainViewModel>()
    private lateinit var stockAdapter: StockAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stock_all, container, false)
        stockAdapter = StockAdapter()
        binding.recyclerAllStock.adapter = stockAdapter
        binding.stokeAllFragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.stockList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            stockAdapter.setData(it as ArrayList<Stock>)
        })
    }

    fun search(view: View) {
        view.findNavController().navigate(R.id.action_stockAllFragment_to_searchFragment)
    }


}