package com.example.stock.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.stock.R
import com.example.stock.data.User
import com.example.stock.databinding.FragmentSellBinding
import com.example.stock.model.*


class SellFragment : Fragment() {
    private lateinit var binding: FragmentSellBinding
    private lateinit var viewModel: SellViewModel
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sell, container, false)

        binding.stock = mainViewModel.getCurrentStock()
        binding.company = mainViewModel.getCurrentCompany()
        binding.user = mainViewModel.userIam.value

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            SellViewModelFactory(mainViewModel.getCurrentStock().price.toInt())
        ).get(SellViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.price.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.sellSellPrice.text = it.toString()

            binding.sellAfterSell.text = if (binding.user?.account?.plus(it)!! < 0) { "거래불가" } else {
                (binding.user?.account?.plus(
                    it
                )).toString()
            }
        })
    }


}