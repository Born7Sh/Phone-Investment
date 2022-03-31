package com.example.stock.views.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.stock.R
import com.example.stock.adapter.NewsAdapter
import com.example.stock.adapter.StockAdapter
import com.example.stock.data.News
import com.example.stock.databinding.FragmentFavoriteBinding

import com.example.stock.databinding.FragmentHomeBinding
import com.example.stock.model.NewsViewModel
import com.example.stock.model.StockViewModel
import kotlinx.android.synthetic.main.activity_main.view.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var stockViewModel: StockViewModel
    private lateinit var stockAdapter: StockAdapter

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)


        stockAdapter = StockAdapter()
        newsAdapter = NewsAdapter()

        binding.recyclerStock.adapter = stockAdapter
        binding.recyclerNews.adapter = newsAdapter
        binding.homeFragment = this


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stockViewModel = ViewModelProvider(this).get(StockViewModel::class.java)
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        binding.stockViewModel = stockViewModel
        binding.newsViewModel = newsViewModel

        stockViewModel.stockList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            stockAdapter.setData(it)
        })

        newsViewModel.newsList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            newsAdapter.setData(it)
        })

    }

    fun btnClick(view: View) {
        binding.scrollView.smoothScrollTo(0, binding.recyclerNews.bottom)
        Toast.makeText(activity, "Button Click", Toast.LENGTH_SHORT).show()
    }

    fun textClick(view: View) {

        view.findNavController().navigate(R.id.action_HomeFragment_to_homeStockFragment)
    }

    fun btnClick2(view: View) {

        view.findNavController().navigate(R.id.action_HomeFragment_to_stockDetailFragment)
    }

}