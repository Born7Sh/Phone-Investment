package com.example.stock.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stock.data.model.Rank
import com.example.stock.databinding.ListRankBinding

class RankAdapter : RecyclerView.Adapter<RankAdapter.MyViewHolder>(){
    var rankList=mutableListOf<Rank>()

    // 생성된 뷰 홀더에 값 지정
    class MyViewHolder(val binding : ListRankBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(currentRank: Rank){
            binding.rank=currentRank
        }
    }

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):MyViewHolder{
        val binding= ListRankBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder:MyViewHolder,position:Int){
        holder.bind(rankList[position])
    }

    // 뷰 홀더의 개수 리턴
    override fun getItemCount():Int{
        return rankList.size
    }

    fun setData(data:ArrayList<Rank>){
        rankList=data
        notifyDataSetChanged()
    }
}