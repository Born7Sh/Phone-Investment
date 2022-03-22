package com.example.stock.data

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Stock (
    @PrimaryKey @ColumnInfo(name = "id")
    val stockId: String,
    val name: String,
    val description: String)