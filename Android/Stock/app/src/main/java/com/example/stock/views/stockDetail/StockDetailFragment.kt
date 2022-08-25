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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.stock.R
import com.example.stock.adapter.CommunityAdapter
import com.example.stock.util.EventObserver
import com.example.stock.data.model.Stock
import com.example.stock.databinding.FragmentStockDetailBinding
import com.example.stock.model.MainViewModel
import com.example.stock.model.StockDetailViewModel
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
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
                binding.heart.setImageResource(R.drawable.icon_heart_red)
            } else if (it == 0) {
                binding.heart.setImageResource(R.drawable.icon_bot_heart10)
            }

        })

        stockDetailViewModel.btnBackClick.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigateUp()
        })

        stockDetailViewModel.btnBuyClick.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(R.id.action_stockDetailFragment_to_buyFragment)
        })

        stockDetailViewModel.btnSellClick.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(R.id.action_stockDetailFragment_to_sellFragment)
        })

        stockDetailViewModel.btnCommunityClick.observe(viewLifecycleOwner, EventObserver {
            val action =
                StockDetailFragmentDirections.actionStockDetailFragmentToCommunityFragment(it)
            findNavController().navigate(action)
        })

        stockDetailViewModel.communityList.observe(viewLifecycleOwner, {
            communityAdapter.setData(it)
        })

        stockDetailViewModel.stockList.observe(viewLifecycleOwner, {


            val dataSet = CandleDataSet(it, "").apply {
                axisDependency = YAxis.AxisDependency.LEFT
                // 심지 부분 설정
                shadowColor = Color.LTGRAY
                shadowWidth = 0.7F
                // 음봉 설정
                decreasingColor = Color.rgb(18, 98, 197)
                decreasingPaintStyle = Paint.Style.FILL
                // 양봉 설정
                increasingColor = Color.rgb(200, 74, 49)
                increasingPaintStyle = Paint.Style.FILL

                neutralColor = Color.rgb(6, 18, 34)
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
            setExtraOffsets(10f, 0f, 10f, 0f);
            // x축 설정
            xAxis.apply {
                isEnabled = true
                textColor = Color.BLACK
                position = XAxis.XAxisPosition.BOTTOM
                // 세로선 표시 여부 설정
                setDrawGridLines(true)
                setDrawAxisLine(false)
                axisLineColor = Color.rgb(50, 59, 76)
                gridColor = Color.rgb(50, 59, 76)
            }
            // 왼쪽 y축 설정
            axisLeft.apply {
                textColor = Color.BLACK
                isEnabled = false
            }
            // 오른쪽 y축 설정
            axisRight.apply {
                isEnabled = true
                // 가로선 표시 여부 설정
                setDrawGridLines(true)
                // 차트의 오른쪽 테두리 라인 설정
                setDrawAxisLine(true)
                textColor = Color.BLACK
                // 가로선 표시 여부 설정

            }
            legend.apply {
                isEnabled = true
                textColor = Color.BLACK
                verticalAlignment = Legend.LegendVerticalAlignment.TOP
                horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
                orientation = Legend.LegendOrientation.HORIZONTAL
                setDrawInside(true)
            }
        }
    }


}