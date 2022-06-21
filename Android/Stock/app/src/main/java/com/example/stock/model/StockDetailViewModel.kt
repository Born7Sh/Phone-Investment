package com.example.stock.model

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.stock.R
import com.example.stock.data.*
import com.example.stock.views.stockDetail.StockDetailFragmentDirections
import com.github.mikephil.charting.data.CandleEntry
import java.text.NumberFormat

class StockDetailViewModel() : ViewModel() {

    private val _stockList = MutableLiveData<ArrayList<CandleEntry>>()
    val stockList: LiveData<ArrayList<CandleEntry>>
        get() = _stockList

    private var items = ArrayList<CandleEntry>()

    // 회사 리스트
    private val _communityList = MutableLiveData<ArrayList<Community>>()
    val communityList: LiveData<ArrayList<Community>>
        get() = _communityList

    private var community = ArrayList<Community>()

    init {
        for (candleStock in DataUtilBar.getCandleStockData()) {
            items.add(
                CandleEntry(
                    candleStock.createdAt.toFloat(),
                    candleStock.shadowHigh,
                    candleStock.shadowLow,
                    candleStock.open,
                    candleStock.close
                )
            )
        }
        _stockList.value = items



        community = arrayListOf(

            Community("김규동", "Apple", "AAPL", "드자가"),
            Community("김차동", "AmerisourceBergen", "ABC", "자 드가자"),
        )
        _communityList.value = community
    }

    fun setUnit(value : Int) : String{
        return when {
            value >= 1E9 -> "${(value.toFloat() / 1E9).toInt()}B"
            value >= 1E6 -> "${(value.toFloat() / 1E6).toInt()}M"
            value >= 1E3 -> "${(value.toFloat() / 1E3).toInt()}K"
            else -> NumberFormat.getInstance().format(value)
        }

    }

    fun btnSellClick(view : View){
        Log.v("items","파는거 입니다. 반갑습니다 허허허")
        view.findNavController().navigate(R.id.action_stockDetailFragment_to_sellFragment)
    }

    fun btnBuyClick(view : View){
        Log.v("items","사는거 입니다. 반갑습니다 허허허")
        view.findNavController().navigate(R.id.action_stockDetailFragment_to_buyFragment)

    }

    fun btnCommunity (view: View){
        Log.v("items","버튼 클립ㄱ!!!! 입니다. 반갑습니다 허허허")
        val action = StockDetailFragmentDirections.actionStockDetailFragmentToCommunityFragment(community.toTypedArray())
        view.findNavController().navigate(action)

    }

}