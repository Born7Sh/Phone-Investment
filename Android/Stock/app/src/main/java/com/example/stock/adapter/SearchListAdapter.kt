package com.example.stock.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import android.R
import android.content.Context
import android.util.Log
import android.view.View

import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.stock.data.News
import com.example.stock.databinding.ListNewsBinding
import com.example.stock.databinding.ListSearchBinding


class SearchListAdapter : RecyclerView.Adapter<SearchListAdapter.MyViewHolder>(), Filterable {
    var unFilteredList: ArrayList<String>
    var filteredList: ArrayList<String>

    class MyViewHolder(val binding: ListSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentString: String) {
            Log.v("items", "binding")
            binding.name = currentString
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        Log.v("items", "viewholder")
        val binding = ListSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(filteredList[position])
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                Log.v("items", "혹시 이게 먼저?")
                val charString = constraint.toString()
                filteredList = if (charString.isEmpty()) {
                    unFilteredList
                } else {
                    val filteringList: ArrayList<String> = ArrayList()
                    for (name in unFilteredList) {
                        if (name.toLowerCase().contains(charString.toLowerCase())) {
                            filteringList.add(name)
                        }
                    }
                    filteringList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                filteredList = results.values as ArrayList<String>
                notifyDataSetChanged()
            }
        }
    }

    init {
        var items = arrayListOf(
            "AmerisourceBergen",
            "아처 대니얼스 미들랜드",
            "암드",
            "앤섬",
            "아메리칸 익스프레스",
        )

        unFilteredList = items
        Log.v("items", unFilteredList[1])
        filteredList = items
        Log.v("items", filteredList[1])
        notifyDataSetChanged()
    }
}