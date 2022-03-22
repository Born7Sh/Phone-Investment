package com.example.stock.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.stock.data.Stock

class FavoriteAdapter : ListAdapter<Stock, RecyclerView.ViewHolder>(StockDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StockViewHolder(
            ListItemStockBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val stock = getItem(position)
        (holder as StockViewHolder).bind(stock)
    }

    class StockViewHolder(
        private val binding: ListItemStockBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.stock?.let { stock ->
                    navigateToStock(stock, it)
                }
            }
        }

        private fun navigateToStock(
            stock: Stock,
            view: View
        ) {
            val direction =
                HomeViewPagerFragmentDirections.actionViewPagerFragmentToStockDetailFragment(
                    stock.stockId
                )
            view.findNavController().navigate(direction)
        }

        fun bind(item: Stock) {
            binding.apply {
                stock = item
                executePendingBindings()
            }
        }
    }
}

private class StockDiffCallback : DiffUtil.ItemCallback<Stock>() {

    override fun areItemsTheSame(oldItem: Stock, newItem: Stock): Boolean {
        return oldItem.stockId == newItem.stockId
    }

    override fun areContentsTheSame(oldItem: Stock, newItem: Stock): Boolean {
        return oldItem == newItem
    }
}