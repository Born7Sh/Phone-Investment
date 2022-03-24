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
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stock.MainActivity
import com.example.stock.R
import com.example.stock.adapter.StockAdapter
import com.example.stock.data.Stock
import com.example.stock.databinding.FragmentFavoriteBinding
import com.example.stock.model.StockViewModel
import java.util.*
import kotlin.collections.ArrayList


class FavoriteFragment : Fragment() {
    var data = MutableLiveData<ArrayList<Stock>>()
    private lateinit var binding: FragmentFavoriteBinding
    private var viewModel: StockViewModel by StockViewModel()
    lateinit var adapter: StockAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}