package com.example.stock.views.stockDetail

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.stock.R
import com.example.stock.adapter.CommunityAdapter
import com.example.stock.data.Stock
import com.example.stock.databinding.FragmentStockDetailBinding
import com.example.stock.model.MainViewModel
import com.example.stock.model.StockDetailViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet


class StockDetailFragment : Fragment() {

    lateinit var binding: FragmentStockDetailBinding
    lateinit var stockDetailViewModel: StockDetailViewModel
    private val mainViewModel by activityViewModels<MainViewModel>()

    private val arg: StockDetailFragmentArgs by navArgs()
    private lateinit var communityAdapter: CommunityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_stock_detail, container, false)
        stockDetailViewModel = ViewModelProvider(this).get(StockDetailViewModel::class.java)
        binding.stock = mainViewModel.getStock(arg.stockId)
        binding.company = mainViewModel.getCompany(arg.stockId)
        binding.viewModel = stockDetailViewModel
        communityAdapter = CommunityAdapter()
        binding.recyclerCommunity.adapter = communityAdapter

        // 메인 뷰모델에 있어야하는데 부담 많이될까봐 뺴옴
        // binding Adapter로도 안됨 데이터 가져와야하니깐
        var stockFavoriteList: ArrayList<Stock>? = mainViewModel.stockFavoriteList.value
        if (stockFavoriteList != null) {
            for (i in stockFavoriteList) {
                if (i == binding.stock) {
                    stockDetailViewModel.favoriteTurnOn()
                    break
                }
            }
        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stockDetailViewModel.isFavorite.observe(viewLifecycleOwner, {


            if (it == 1) {
                binding.heart.setImageResource(R.drawable.heart_red)
            } else if (it == 0) {
                binding.heart.setImageResource(R.drawable.heart10)
            }

        })

        stockDetailViewModel.communityList.observe(viewLifecycleOwner, {
            communityAdapter.setData(it)
        })

        stockDetailViewModel.stockList.observe(viewLifecycleOwner, {


            val dataSet = CandleDataSet(it, "").apply {
                // 심지 부분
                shadowColor = Color.LTGRAY
                shadowWidth = 0.7F

                // 음봄
                decreasingColor = Color.BLUE
                decreasingPaintStyle = Paint.Style.FILL
                // 양봉
                increasingColor = Color.RED
                increasingPaintStyle = Paint.Style.FILL

                neutralColor = Color.DKGRAY
                setDrawValues(false)
                // 터치시 노란 선 제거
                highLightColor = Color.TRANSPARENT
            }

            binding.cdChart.apply {
                this.data = CandleData(dataSet)
                requestDisallowInterceptTouchEvent(false)
                invalidate()
            }

            initChart()
        })
    }

    private fun initChart() {
        binding.cdChart.apply {
            description.isEnabled = false
//            setMaxVisibleValueCount(200)
            setTouchEnabled(false) // 터치 유무
            setPinchZoom(false)
//            setDrawGridBackground(false)
            setExtraOffsets(10f, 0f, 40f, 0f);
            // x축 설정
            xAxis.apply {
                x
                isEnabled = true
                textColor = Color.BLACK
                position = XAxis.XAxisPosition.BOTTOM
                // 세로선 표시 여부 설정
                setDrawGridLines(true)
                setDrawAxisLine(true)
                axisLineColor = Color.rgb(50, 59, 76)
                gridColor = Color.rgb(50, 59, 76)
            }
            // 왼쪽 y축 설정
            axisLeft.apply {
                textColor = Color.BLACK
                isEnabled = true
            }
            // 오른쪽 y축 설정
            axisRight.apply {
                isEnabled = true
                setDrawGridLines(true);
                setDrawAxisLine(true);
//                setLabelCount(7, false)
                textColor = Color.BLACK
                // 가로선 표시 여부 설정

            }
            binding.cdChart.legend.isEnabled = true
        }
    }


}