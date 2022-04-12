package com.example.stock.views.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.stock.R
import com.example.stock.adapter.RankAdapter
import com.example.stock.databinding.FragmentRankBinding
import com.example.stock.model.MainViewModel


class RankFragment : Fragment() {
    lateinit var binding: FragmentRankBinding
    private val mainViewModel by activityViewModels<MainViewModel>()
    private lateinit var rankAdapter: RankAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rank, container, false)
        rankAdapter = RankAdapter()
        binding.recyclerAllRank.adapter = rankAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.rankList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            rankAdapter.setData(it)
        })
    }


}