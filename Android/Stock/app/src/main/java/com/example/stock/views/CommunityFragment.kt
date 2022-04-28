package com.example.stock.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.stock.R
import com.example.stock.adapter.CommunityAdapter
import com.example.stock.databinding.FragmentCommunityBinding


class CommunityFragment : Fragment() {

    lateinit var binding: FragmentCommunityBinding
    private lateinit var communityAdapter: CommunityAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.v("items", "일단 넘어옴")

        val args: CommunityFragmentArgs by navArgs()
        val itemList = args.community.toCollection(ArrayList())

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_community, container, false)
        communityAdapter = CommunityAdapter()
        communityAdapter.setData(itemList)
        binding.recyclerCommunity.adapter = communityAdapter
        return binding.root
    }


}
