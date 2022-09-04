package com.example.stock.views.stockAll

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.stock.R
import com.example.stock.adapter.StockAdapter
import com.example.stock.data.model.Stock
import com.example.stock.data.repository.StockRepository
import com.example.stock.databinding.FragmentStockAllBinding
import com.example.stock.viewmodel.FavoriteViewModel
import com.example.stock.viewmodel.MainViewModel
import com.example.stock.viewmodel.RepositoryViewModelFactory
import com.example.stock.viewmodel.StockAllViewModel

class StockAllFragment : Fragment() {
    lateinit var binding: FragmentStockAllBinding
    private lateinit var repositoryViewModelFactory: RepositoryViewModelFactory
    lateinit var stockAllViewModel: StockAllViewModel
    private val mainViewModel by activityViewModels<MainViewModel>()
    private lateinit var stockAdapter: StockAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stock_all, container, false)
        repositoryViewModelFactory = RepositoryViewModelFactory(StockRepository())
        stockAllViewModel =
            ViewModelProvider(this, repositoryViewModelFactory).get(StockAllViewModel::class.java)

        stockAdapter = StockAdapter()
        binding.recyclerAllStock.adapter = stockAdapter
        binding.stokeAllFragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stockAllViewModel.updateAllStockList()

        stockAllViewModel.allStockList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            stockAdapter.setData(it as ArrayList<Stock>)
        })
        mainViewModel.stateMessage.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            Log.d("items", "stockAllFragment updateAllStockList() 호출")
            stockAllViewModel.updateAllStockList()
        })

    }

    fun search(view: View) {
        view.findNavController().navigate(R.id.action_stockAllFragment_to_searchFragment)
    }


}