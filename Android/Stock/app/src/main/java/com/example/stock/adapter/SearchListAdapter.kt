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
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.stock.data.News
import com.example.stock.data.Stock
import com.example.stock.databinding.ListNewsBinding
import com.example.stock.databinding.ListSearchBinding
import com.example.stock.views.SearchFragmentDirections
import com.example.stock.views.home.HomeFragmentDirections


class SearchListAdapter : RecyclerView.Adapter<SearchListAdapter.MyViewHolder>(), Filterable {
    var unFilteredList: ArrayList<String>
    var filteredList: ArrayList<String>

    class MyViewHolder(val binding: ListSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                binding.stockItemName?.let { stock ->
                    var stockID = binding.name ?: "Apple"
                    navigateToDetailStock(stockID, it)
                }
            }
        }

        private fun navigateToDetailStock(
            stockId: String,
            view: View
        ) {
            var sd = stockId
            val direction =
                SearchFragmentDirections.actionSearchFragmentToStockDetailFragment(sd)
            view.findNavController()
                .navigate(direction)
        }


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
            "Apple",
            "AmerisourceBergen",
            "Abbot",
            "ADM",
            "AIG",
            "AMD",
            "Amazon",
            "Anthem",
            "American Express",
            "BOEING",
            "Bank of America",
            "BEST BUY",
            "CITI",
            "CardinalHealth",
            "CAT",
            "chico's",
            "COMCAST",
            "ConocoPhillips",
            "Costoco",
            "coupang",
            "cisco",
            "CVSHealth",
            "Chevron",
            "DELTA",
            "DUPONT",
            "DELL",
            "Walt Disney",
            "Fored",
            "Meta",
            "FedEx",
            "GENERAL DYNAMICS",
            "General Electric",
            "CHEVROLET",
            "Google",
            "GoldmanSachs",
            "HCA",
            "THE HOME DEPOT",
            "HESS",
            "THE HARTFORD",
            "Honeywell",
            "HP",
            "IBM",
            "intel",
            "Johnson Controls",
            "Johnson & Johnson",
            "J.P.Morgan",
            "CocaCola",
            "Kroger",
            "LIBERTY GLOBAL",
            "LOCKHED MARTIN",
            "MCKESSON",
            "MetLife",
            "MERCK",
            "Marathon OIL",
            "Morgan Stanley",
            "Microsoft",
            "NORTHROP GRUMMAN",
            "NVIDIA",
            "PEPSI",
            "PFIZER",
            "P&G",
            "PHOLIP MORRIS",
            "Invesco",
            "RITE AID",
            "Raytheon",
            "STATE STREET",
            "Sysco",
            "AT&T",
            "TARGET",
            "TRAVELERS",
            "TESLA",
            "UnitedHealth Group",
            "ups",
            "VALERO",
            "verizon",
            "Alliance Boots",
            "WELLS FARGO",
            "Walmart",
            "ExxonMobil",
        )

        unFilteredList = items
        Log.v("items", unFilteredList[1])
        filteredList = items
        Log.v("items", filteredList[1])
        notifyDataSetChanged()
    }
}