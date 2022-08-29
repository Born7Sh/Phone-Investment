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
import com.example.stock.viewmodel.AnalysisViewModel
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate


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
                setDrawCircles(true)
                setDrawFilled(true)
                color = ColorTemplate.getHoloBlue()
                highLightColor = Color.TRANSPARENT
                valueTextSize = 0F
                lineWidth = 1.5F
                circleRadius = 2F
                circleHoleRadius = 3F
                mode = LineDataSet.Mode.CUBIC_BEZIER // 둥글게 만드는거
                cubicIntensity = 0.1F
                fillAlpha = 50
                fillColor = ColorTemplate.getHoloBlue()

            }

            val lineData = LineData(dataSet)
            binding.lineChart.run {
                data = lineData
                description.isEnabled = false // 하단 Description Label 제거함
                animateXY(1000, 1000)
                invalidate() // refresh
            }

            // 범례
            binding.lineChart.legend.apply {
                isEnabled = false // 사용하지 않음
            }
            // Y 축
            binding.lineChart.axisLeft.apply {
                // 라벨, 축라인, 그리드 사용하지 않음
                setDrawLabels(true)
                setDrawAxisLine(false)
                setDrawGridLines(false)
                axisMinimum = 1000.0f
                axisMaximum = 10000.0f

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