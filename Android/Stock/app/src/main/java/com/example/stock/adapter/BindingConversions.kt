package com.example.stock.adapter

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.stock.R

object BindingConversions {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView : ImageView, url : Int){

        Glide.with(imageView.context).load(url)
            .error(R.drawable.c_aapl)
            .into(imageView)
    }


}