package com.example.stock.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity
data class Stock(
    @PrimaryKey(autoGenerate = true)
    val symbol: String, // 영어 약자
    val stock_datetime: String, // 날짜
    val price: String,
    val a_price: String,
    val symbol_ko: String,
    val symbol_en: String,
    val imgSrc: Int // 이미지 위치
)

