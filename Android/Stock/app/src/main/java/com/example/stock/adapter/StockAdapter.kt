package com.example.stock.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.stock.R
import com.example.stock.data.model.Stock
import com.example.stock.databinding.ListStockBinding
import com.example.stock.views.home.HomeFragmentDirections
import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.stock.views.favorite.FavoriteFragmentDirections
import com.example.stock.views.stockAll.StockAllFragmentDirections
import kotlinx.android.synthetic.main.fragment_home.view.*


class StockAdapter : RecyclerView.Adapter<StockAdapter.MyViewHolder>() {
    var stockList = mutableListOf<Stock>()

    // 생성된 뷰 홀더에 값 지정
    class MyViewHolder(val binding: ListStockBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                binding.stock?.let { stock ->
                    navigateToDetailStock(stock, it)

                }
            }
        }

        private fun navigateToDetailStock(
            stock: Stock,
            view: View
        ) {
            val args = Bundle()
            args.putString("stockId", stock.symbol_en)

            val currentFragment = view.findNavController().currentDestination!!.id
            Log.v("items", currentFragment.toString())
            Log.v("items", R.id.HomeFragment.toString())
            var sd = stock.symbol_en
            var direction: NavDirections? = null
            if (currentFragment == R.id.HomeFragment) {
                direction =
                    HomeFragmentDirections.actionHomeFragmentToStockDetailFragment(sd)
            } else if (currentFragment == R.id.FavoriteFragment) {
                direction =
                    FavoriteFragmentDirections.actionFavoriteFragmentToStockDetailFragment(sd)

            } else if (currentFragment == R.id.stockAllFragment) {
                direction =
                    StockAllFragmentDirections.actionStockAllFragmentToStockDetailFragment(sd)
            }
            if (direction != null) {
                view.findNavController().navigate(direction)
            }
        }

        fun bind(currentStock: Stock) {
            binding.stock = currentStock
        }

    }

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(stockList[position])
    }

    // 뷰 홀더의 개수 리턴
    override fun getItemCount(): Int {
        return stockList.size
    }

    fun setData(data: List<Stock>) {
        stockList = data.toMutableList()
        notifyDataSetChanged()
    }
}