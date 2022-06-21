package com.example.stock.views.stockDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.stock.R
import com.example.stock.databinding.FragmentBuyBinding
import com.example.stock.model.BuyViewModel
import com.example.stock.model.BuyViewModelFactory
import com.example.stock.model.MainViewModel

class BuyFragment : Fragment() {


    private lateinit var binding: FragmentBuyBinding
    private lateinit var viewModel: BuyViewModel
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_buy, container, false)

        binding.stock = mainViewModel.getCurrentStock()
        binding.company = mainViewModel.getCurrentCompany()
        binding.user = mainViewModel.userIam.value
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            BuyViewModelFactory(mainViewModel.getCurrentStock().price.toInt())
        ).get(BuyViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.price.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.buyBuyPrice.text = it.toString()

            if (binding.user?.account?.minus(it)!! < 0) {
                binding.buyAfterBuy.text = "거래불가"
                binding.btnBuy.isEnabled = false
                binding.btnBuy.isClickable = false
                binding.btnBuy.setBackgroundResource(R.drawable.button_round_false)
                binding.btnBuy.setTextColor(resources.getColor(R.color.grey))

            } else {
                binding.buyAfterBuy.text =  (binding.user?.account?.minus(
                    it
                )).toString()

                binding.btnBuy.isEnabled = true
                binding.btnBuy.isClickable = true
                binding.btnBuy.setBackgroundResource(R.drawable.button_round_blue)
                binding.btnBuy.setTextColor(resources.getColor(R.color.white))
            }

        })
    }

}