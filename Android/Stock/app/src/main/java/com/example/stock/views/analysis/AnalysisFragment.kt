package com.example.stock.views.analysis

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.stock.R
import com.example.stock.databinding.FragmentAnalysisBinding
import com.example.stock.model.AnalysisViewModel
import com.example.stock.model.CommunityViewModel
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


class AnalysisFragment : Fragment() {
    lateinit var binding: FragmentAnalysisBinding
    private val viewModel by viewModels<AnalysisViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_analysis, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel

        viewModel.barDataEntryList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

            val dataSet = LineDataSet(it, "").apply {
                setDrawCircles(false)
                color = Color.RED
                highLightColor = Color.TRANSPARENT
                valueTextSize = 0F
                lineWidth = 1.5F
            }

            val lineData = LineData(dataSet)
            binding.lineChart.run {
                data = lineData
                description.isEnabled = false // 하단 Description Label 제거함
                invalidate() // refresh
            }

            val averageLine = LimitLine(viewModel.getAverage()).apply {
                lineWidth = 1F
                enableDashedLine(4F, 10F, 10F)
                lineColor = Color.DKGRAY
            }

            // 범례
            binding.lineChart.legend.apply {
                isEnabled = false // 사용하지 않음
            }
            // Y 축
            binding.lineChart.axisLeft.apply {
                // 라벨, 축라인, 그리드 사용하지 않음
                setDrawLabels(true)
                setDrawAxisLine(true)
                setDrawGridLines(true)

                // 한계선 추가
                removeAllLimitLines()
                addLimitLine(averageLine)
            }
            binding.lineChart.axisRight.apply {
                // 우측 Y축은 사용하지 않음
                isEnabled = false
            }
            // X 축
            binding.lineChart.xAxis.apply {
                // x축 값은 투명으로
                textColor = Color.TRANSPARENT
                // 축라인, 그리드 사용하지 않음
                setDrawAxisLine(false)
                setDrawGridLines(false)
            }

        })


    }

}