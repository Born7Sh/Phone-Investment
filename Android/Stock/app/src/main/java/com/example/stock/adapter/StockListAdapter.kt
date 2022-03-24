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

class StockListAdapter : List<Stock, StockListAdapter.StockViewHolder>(StocksComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        return StockViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.word)
    }

    class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var stock_item_title = itemView.stock_item_title
        var stock_item_price = itemView.stock_item_price

        fun bind(text: String?) {
                stock_item_title.text = text
                stock_item_price.text = text
        }

        companion object {
            fun create(parent: ViewGroup): StockViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_stock, parent, false)
                return StockViewHolder(view)
            }
        }
    }

    class StocksComparator : DiffUtil.ItemCallback<Stock>() {
        override fun areItemsTheSame(oldItem: Stock, newItem: Stock): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Stock, newItem: Stock): Boolean {
            return oldItem.name == newItem.name
        }
    }
}
