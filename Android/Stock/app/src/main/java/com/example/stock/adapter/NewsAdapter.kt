package com.example.stock.adapter

import com.example.stock.databinding.ListNewsBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.example.stock.data.News
class NewsAdapter :RecyclerView.Adapter<NewsAdapter.MyViewHolder>(){
    var newsList=mutableListOf<News>()

    // 생성된 뷰 홀더에 값 지정
    class MyViewHolder(val binding :ListNewsBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(currentNews:News){
            binding.news=currentNews
        }
    }

    // 어떤 xml 으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):MyViewHolder{
        val binding=ListNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder:MyViewHolder,position:Int){
        holder.bind(newsList[position])
    }

    // 뷰 홀더의 개수 리턴
    override fun getItemCount():Int{
        return newsList.size
    }

    fun setData(data:ArrayList<News>){
        newsList=data
        notifyDataSetChanged()
    }
}
