package com.example.stock.views.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.stock.R
import com.example.stock.adapter.StockAdapter
import com.example.stock.databinding.FragmentFavoriteBinding
import com.example.stock.model.MainViewModel
import com.example.stock.model.StockViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    private val mainViewModel by activityViewModels<MainViewModel>()
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

        mainViewModel.stockFavoriteList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            stockAdapter.setData(it)
        })


    }

}