package com.example.stock.views.stockDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.stock.R
import com.example.stock.data.repository.StockRepository
import com.example.stock.databinding.FragmentSellBinding
import com.example.stock.global.GlobalApplication
import com.example.stock.util.EventObserver
import com.example.stock.viewmodel.*


class SellFragment : Fragment() {
    private lateinit var binding: FragmentSellBinding
    private lateinit var sellViewModel: SellViewModel
    private val mainViewModel by activityViewModels<MainViewModel>()
    private lateinit var repositoryViewModelFactory: RepositoryViewModelFactory

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

        binding.contentLoading.hide()
        repositoryViewModelFactory = RepositoryViewModelFactory(StockRepository())
        sellViewModel =
            ViewModelProvider(this, repositoryViewModelFactory).get(SellViewModel::class.java)

        binding.viewModel = sellViewModel

        //22/09/05 update 기능 추가
        mainViewModel.stateMessage.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            sellViewModel.updateStockPrice()
        })
        //22/09/05 update 기능 추가
        sellViewModel.currentStock.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.stock = it
        })
        // 22/09/05 매수 가격 UI 바꾸기.
        sellViewModel.totalPrice.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.sellSellPrice.text = it.toString()
        })


        sellViewModel.retroState.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.contentLoading.hide()
            when (it) {
                "200" -> {
                    Toast.makeText(context, "성공", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()

                }
                "300" -> {
                    Toast.makeText(context, "실패 : 네트워크 상태가 불안정합니다!", Toast.LENGTH_SHORT).show()
                }
                "100" -> {
                    Toast.makeText(context, "exception", Toast.LENGTH_SHORT).show()
                }
            }
        })

        sellViewModel.clickAble.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it == true) {
                binding.btnSell.isEnabled = true
                binding.btnSell.isClickable = true
                binding.btnSell.setBackgroundResource(R.drawable.background_button_round_red)
                binding.btnSell.setTextColor(resources.getColor(R.color.red))
            } else {
                binding.btnSell.isEnabled = false
                binding.btnSell.isClickable = false
                binding.btnSell.setBackgroundResource(R.drawable.background_button_round_false)
                binding.btnSell.setTextColor(resources.getColor(R.color.grey))
            }
        })

        sellViewModel.price.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.sellSellPrice.text = it.toString()
        })

        //22/09/05 뒤로가기 버튼 클릭 이벤트 추가
        sellViewModel.backBtnClick.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigateUp()
        })
        //22/09/05 구매 버튼 클릭 이벤트 추가
        sellViewModel.sellBtnClick.observe(viewLifecycleOwner, EventObserver {
            //22/09/05 구매 요청
            // 일단 contentLoading 띄워놓고
            // 답 오면 처리하기
            binding.contentLoading.show()
            sellViewModel.retroRequestBuy()
        })

    }


}