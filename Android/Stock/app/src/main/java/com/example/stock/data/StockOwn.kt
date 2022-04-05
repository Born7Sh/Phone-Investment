package com.example.stock.data

import androidx.room.PrimaryKey

data class StockOwn(
    @PrimaryKey(autoGenerate = true)
    val stockId: String, // 순서
    val name: String, // 회사 이름
    val c_name: String, // 회사 주식 이름
    val price: String,
    val Investment: String,
    val imgSrc: String, // 이미지 위치
)