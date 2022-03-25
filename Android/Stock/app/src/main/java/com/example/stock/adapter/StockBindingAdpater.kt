package com.example.stock.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.stock.data.Stock

object StockBindingAdpater {
    @BindingAdapter("items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, items: ArrayList<Stock>) {

        if (recyclerView.adapter == null) {
            val adapter = StockAdatper()
            adapter.setHasStableIds(true)
            recyclerView.adapter = adapter

            val stockAdapter = recyclerView.adapter as StockAdatper

            stockAdapter.stockList = items
            stockAdapter.notifyDataSetChanged()
        }
    }
}

