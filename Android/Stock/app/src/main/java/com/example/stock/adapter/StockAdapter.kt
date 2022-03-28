package com.example.stock.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stock.data.Stock
import com.example.stock.databinding.ListStockBinding

class StockAdapter : RecyclerView.Adapter<StockAdapter.MyViewHolder>(){
    var stockList = mutableListOf<Stock>()

    // 생성된 뷰 홀더에 값 지정
    class MyViewHolder(val binding : ListStockBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentStock : Stock) {
            binding.stock = currentStock
        }
    }

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListStockBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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

    fun setData(data : ArrayList<Stock>){
        stockList = data
        notifyDataSetChanged()
    }
}