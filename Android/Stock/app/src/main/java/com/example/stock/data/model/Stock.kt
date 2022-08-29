package com.example.stock.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Stock")
data class Stock(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("symbol")
    var symbol: String, // 영어 약자
    @SerializedName("stock_datetime")
    var stock_datetime: String, // 날짜
    @SerializedName("price")
    var price: Float,
    @SerializedName("a_price")
    var a_price: Float,
    @SerializedName("symbol_ko")
    var symbol_ko: String,
    @SerializedName("symbol_en")
    var symbol_en: String,

    val imgSrc: String, // 이미지 위치

    var stock_classification : Int
    // 0 기본값
    // 1 구매한 상태
    // 2 즐겨찾기 상태
    // 3 구매,즐겨 다한 상태
    )

