package com.example.stock.views.stockDetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.stock.R
import com.example.stock.data.repository.StockRepository
import com.example.stock.databinding.FragmentBuyBinding
import com.example.stock.global.GlobalApplication
import com.example.stock.util.EventObserver
import com.example.stock.viewmodel.BuyViewModel
import com.example.stock.viewmodel.MainViewModel
import com.example.stock.viewmodel.RepositoryViewModelFactory

class BuyFragment : Fragment() {


    private lateinit var binding: FragmentBuyBinding
    private lateinit var buyViewModel: BuyViewModel
    private val mainViewModel by activityViewModels<MainViewModel>()
    private lateinit var repositoryViewModelFactory: RepositoryViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_buy, container, false)

        binding.stock = GlobalApplication.currentStock
        binding.user = mainViewModel.userIam.value
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contentLoading.hide()
        repositoryViewModelFactory = RepositoryViewModelFactory(StockRepository())
        buyViewModel =
            ViewModelProvider(this, repositoryViewModelFactory).get(BuyViewModel::class.java)

        binding.viewModel = buyViewModel

        //22/09/05 update 기능 추가
        mainViewModel.stateMessage.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            buyViewModel.updateStockPrice()
        })
        //22/09/05 update 기능 추가
        buyViewModel.currentStock.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.stock = it
        })
        // 22/09/05 매수 가격 UI 바꾸기.
        buyViewModel.totalPrice.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.buyBuyPrice.text = it.toString()
        })


        buyViewModel.retroState.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
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
        buyViewModel.clickAble.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it == true) {
                binding.btnBuy.isEnabled = true
                binding.btnBuy.isClickable = true
                binding.btnBuy.setBackgroundResource(R.drawable.background_button_round_blue)
                binding.btnBuy.setTextColor(resources.getColor(R.color.white))
            } else {
                binding.btnBuy.isEnabled = false
                binding.btnBuy.isClickable = false
                binding.btnBuy.setBackgroundResource(R.drawable.background_button_round_false)
                binding.btnBuy.setTextColor(resources.getColor(R.color.grey))
            }
        })

        buyViewModel.price.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.buyBuyPrice.text = it.toString()

        })

        //22/09/05 뒤로가기 버튼 클릭 이벤트 추가
        buyViewModel.backBtnClick.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigateUp()
        })
        //22/09/05 구매 버튼 클릭 이벤트 추가
        buyViewModel.buyBtnClick.observe(viewLifecycleOwner, EventObserver {
            //22/09/05 구매 요청
            // 일단 contentLoading 띄워놓고
            // 답 오면 처리하기
            binding.contentLoading.show()
            buyViewModel.retroRequestBuy()
        })
    }

}