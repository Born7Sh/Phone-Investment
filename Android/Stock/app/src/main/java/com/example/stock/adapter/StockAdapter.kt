package com.example.stock.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.stock.R
import com.example.stock.data.Stock
import kotlinx.android.synthetic.main.list_stock.view.*

class StockAdapter (var data: LiveData<ArrayList<Stock>>) : RecyclerView.Adapter<StockAdapter.MyViewHolder>() {
// 회사 정보 보여주는 곳
    inner class MyViewHolder constructor(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_stock, parent, false)
    ) {
        var stock_item_title = itemView.stock_item_title
        var stock_item_price = itemView.stock_item_price
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(parent)
    }

    override fun getItemCount(): Int {
        Log.e("datasize", "" + data.value!!.size)
        return data.value!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Log.e("dataValue1", data.value!!.get(0).toString())
        Log.e("dataValue2", data.value!!.get(1).toString())
        Log.e("dataValue3", data.value!!.get(2).toString())

        data.value!!.get(position).let { item ->
            with(holder) {
                stock_item_title.text = item.name
                stock_item_price.text = item.price
            }
        }
    }
}