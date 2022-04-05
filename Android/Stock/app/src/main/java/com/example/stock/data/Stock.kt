package com.example.stock.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity
data class Stock(
    @PrimaryKey(autoGenerate = true)
    val stockId: String, // 순서
    val name: String, // 회사 이름
    val c_name : String, // 회사 주식 이름
    val price: String, // 1주당 가격
    val variation : String, // 변동폭
    val imgSrc: Int // 이미지 위치
)

