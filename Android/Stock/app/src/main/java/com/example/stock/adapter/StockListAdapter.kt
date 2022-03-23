package com.example.stock.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.stock.R

class StockListAdapter(
    private val itemClick: (StockNotice) -> Unit,
    private val numClick: (StockNotice) -> Unit
) : ListAdapter<StockNotice, StockListAdapter.ViewHolder>(diffUtil) {
    //리스트 선언 필요X //
    // private val items = mutableListOf<EmployNotice>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemStockBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_stock,
            parent,
            false
        )
        val viewHolder = ViewHolder(binding) binding . apply {
            root.setOnClickListener {
                itemClick(
                    getItem(viewHolder.adapterPosition)
                ) //getItem()으로 아이템 가져옴
            } employTvNum . setOnClickListener { numClick(getItem(viewHolder.adapterPosition)) } // 이부분 고치자
        } return viewHolder
    }

    //getItemCount() 오버라이딩 메서드 사라짐
    // override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)) //변경된 점 -> getItem(position) 메서드가 생겼다.
    }

    class ViewHolder(private val binding: ItemStockBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StockNotice) {
            binding.item = item binding . executePendingBindings ()
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<StockNotice>() {
            override fun areContentsTheSame(oldItem: StockNotice, newItem: StockNotice) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: StockNotice, newItem: StockNotice) =
                oldItem.link == newItem.link
        }
    }
}

