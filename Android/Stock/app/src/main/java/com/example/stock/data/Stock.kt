package com.example.stock.data

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Stock (
    val stockId: String,
    val name: String,
    val price: String,
    val description: String)