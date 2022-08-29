package com.example.stock.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.stock.R

object BindingConversions {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView : ImageView, url : String){

        val context: Context = imageView.context
        val resId: Int = context.resources.getIdentifier(url, "drawable", context.packageName)

        Glide.with(imageView.context)
            .load(resId)
            .error(R.drawable.c_aapl)
            .into(imageView)
    }


}