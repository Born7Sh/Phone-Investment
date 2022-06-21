package com.example.stock.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stock.data.Community
import com.example.stock.data.News
import com.example.stock.databinding.ListCommunityBinding
import com.example.stock.databinding.ListNewsBinding
import com.example.stock.views.stockDetail.CommunityFragmentArgs

class CommunityAdapter : RecyclerView.Adapter<CommunityAdapter.MyViewHolder>(){
    var communityList=mutableListOf<Community>()

    // 생성된 뷰 홀더에 값 지정
    class MyViewHolder(val binding : ListCommunityBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(currentCommunity: Community){
            binding.community=currentCommunity
        }
    }

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):MyViewHolder{
        val binding= ListCommunityBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder:MyViewHolder,position:Int){
        holder.bind(communityList[position])
    }

    // 뷰 홀더의 개수 리턴
    override fun getItemCount():Int{
        return communityList.size
    }

    fun setData(data:ArrayList<Community>){
        communityList=data
        notifyDataSetChanged()
    }
}