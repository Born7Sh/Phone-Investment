package com.example.stock.views.stockDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.stock.R
import com.example.stock.databinding.FragmentSellBinding
import com.example.stock.global.GlobalApplication
import com.example.stock.viewmodel.*


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

        binding.stock = GlobalApplication.currentStock
        binding.user = mainViewModel.userIam.value

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            SellViewModelFactory(GlobalApplication.currentStock.price.toInt())
        ).get(SellViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.price.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.sellSellPrice.text = it.toString()

            // 조건 수정 필요
            if (binding.user?.account?.plus(it)!! > 1111111) {
                binding.sellAfterSell.text = "거래불가"
                binding.btnSell.isEnabled = false
                binding.btnSell.isClickable = false
                binding.btnSell.setBackgroundResource(R.drawable.background_button_round_false)
                binding.btnSell.setTextColor(resources.getColor(R.color.grey))
            } else {
                binding.sellAfterSell.text = (binding.user?.account?.plus(it)).toString()
                binding.btnSell.isEnabled = true
                binding.btnSell.isClickable = true
                binding.btnSell.setBackgroundResource(R.drawable.background_button_round_red)
                binding.btnSell.setTextColor(resources.getColor(R.color.red))
            }


        })
    }


}