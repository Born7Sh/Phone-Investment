package com.example.stock.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stock.data.News
import com.example.stock.data.Stock
import com.example.stock.databinding.ListNewsBinding
import com.example.stock.databinding.ListStockBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    var newsList = mutableListOf<News>()
    var stockList = mutableListOf<Stock>()

    // 생성된 뷰 홀더에 값 지정
    class MyViewHolder(val binding1: ListNewsBinding, val binding2: ListStockBinding) :
        RecyclerView.ViewHolder(binding1.root) {
        fun bind(currentNews: News, currentStock: Stock) {
            binding1.news = currentNews
            binding2.stock = currentStock
        }
    }

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding1 = ListNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val binding2 = ListStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding1, binding2)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(newsList[position],stockList[position])
    }

    // 뷰 홀더의 개수 리턴
    // 수정되어야 함.
    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setData(data: ArrayList<News>) {
        newsList = data
        notifyDataSetChanged()
    }
}