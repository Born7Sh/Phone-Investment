package com.example.stock.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "StockEntire")
class StockEntire(
    @PrimaryKey @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "stock_datetime") val stock_datetime: String,
    @ColumnInfo(name = "price") val price: Float,
    @ColumnInfo(name = "a_price") val a_price: Float,
    @ColumnInfo(name = "symbol_ko") val symbol_ko: String,
    @ColumnInfo(name = "symbol_en") val symbol_en: String,
    val imgSrc: Int // 이미지 위치
)

@Entity(tableName = "StockFavorite")
class StockFavorite(
    @PrimaryKey @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "stock_datetime") val stock_datetime: String,
    @ColumnInfo(name = "price") val price: Float,
    @ColumnInfo(name = "a_price") val a_price: Float,
    @ColumnInfo(name = "symbol_ko") val symbol_ko: String,
    @ColumnInfo(name = "symbol_en") val symbol_en: String,
    val imgSrc: Int // 이미지 위치
)

@Entity(tableName = "StockOwn")
class StockOwn(
    @PrimaryKey @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "stock_datetime") val stock_datetime: String,
    @ColumnInfo(name = "price") val price: Float,
    @ColumnInfo(name = "a_price") val a_price: Float,
    @ColumnInfo(name = "symbol_ko") val symbol_ko: String,
    @ColumnInfo(name = "symbol_en") val symbol_en: String,
    val imgSrc: Int // 이미지 위치
)