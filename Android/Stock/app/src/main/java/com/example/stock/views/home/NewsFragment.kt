package com.example.stock.views.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.stock.R
import com.example.stock.adapter.NewsAdapter
import com.example.stock.databinding.FragmentNewsBinding
import com.example.stock.viewmodel.MainViewModel

class NewsFragment : Fragment() {

    lateinit var binding : FragmentNewsBinding
    private val mainViewModel by activityViewModels<MainViewModel>()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        newsAdapter = NewsAdapter()
        binding.recyclerAllNews.adapter = newsAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.newsList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            newsAdapter.setData(it)
        })
    }

}