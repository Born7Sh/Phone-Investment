package com.example.stock.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//@Entity
data class Stock(
    @SerializedName("symbol")
    val symbol: String, // 영어 약자
    @SerializedName("stock_datetime")
    val stock_datetime: String, // 날짜
    @SerializedName("price")
    val price: Float,
    @SerializedName("a_price")
    val a_price: Float,
    @SerializedName("symbol_ko")
    val symbol_ko: String,
    @SerializedName("symbol_en")
    val symbol_en: String,

    val imgSrc: Int // 이미지 위치
)

