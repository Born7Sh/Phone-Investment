package com.example.stock.views.favorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.example.stock.R
import com.example.stock.adapter.StockAdapter
import com.example.stock.data.Stock
import com.example.stock.databinding.FragmentFavoriteBinding
import com.example.stock.viewmodels.StockViewModel
import java.util.*
import kotlin.collections.ArrayList


class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    val viewModel: StockViewModel by viewModels()
    var data = MutableLiveData<ArrayList<Stock>>()
    lateinit var adapter: StockAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate<FragmentFavoriteBinding>(inflater,
            R.layout.fragment_favorite,container,false)

        binding.recycler

        var list = ArrayList<User>()
        list.add( User("1213123","123123"))
        list.add( User("1213123","123123"))
        list.add( User("1213123","123123"))
        viewmodel.liveData.postValue(list)

        val dataObserver: Observer<ArrayList<Stock>> =
            Observer { livedata ->
                data.value = livedata
                var newAdapter = StockAdapter(data)
                binding.recycler.adapter = newAdapter
            }

        viewModel.liveData.observe(this, dataObserver)

        return binding.root
    }

}