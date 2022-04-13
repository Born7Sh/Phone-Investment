package com.example.stock.views

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.stock.R
import com.example.stock.databinding.FragmentStockDetailBinding
import com.example.stock.model.MainViewModel
import com.example.stock.model.StockDetailViewModel
import com.example.stock.model.StockViewModel
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet


class StockDetailFragment : Fragment() {

    lateinit var binding: FragmentStockDetailBinding
    lateinit var stockDetailViewModel: StockDetailViewModel
    private val mainViewModel by activityViewModels<MainViewModel>()

    private val arg: StockDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_stock_detail, container, false)

        stockDetailViewModel = ViewModelProvider(this).get(StockDetailViewModel::class.java)
        binding.stock = mainViewModel.getStock(arg.stockId)
        binding.viewModel = stockDetailViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stockDetailViewModel.stockList.observe(viewLifecycleOwner, {

            val dataSet = CandleDataSet(it, "").apply {
                // 심지 부분
                shadowColor = Color.LTGRAY
                shadowWidth = 1F

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

            binding.cdChart.axisLeft.run {
                setDrawAxisLine(false)
                setDrawGridLines(false)
                textColor = Color.TRANSPARENT
            }

            binding.cdChart.axisRight.run {
                isEnabled = false
            }

            // X 축
            binding.cdChart.xAxis.run {
                textColor = Color.TRANSPARENT
                setDrawAxisLine(false)
                setDrawGridLines(false)
                setAvoidFirstLastClipping(true)
            }

            // 범례
            binding.cdChart.legend.run {
                isEnabled = false
            }

            binding.cdChart.apply {
                this.data = CandleData(dataSet)
                description.isEnabled = false
                isHighlightPerDragEnabled = true
                requestDisallowInterceptTouchEvent(true)
                invalidate()
            }
        })
    }


}