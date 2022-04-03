package com.example.stock.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity
data class Stock(
    @PrimaryKey(autoGenerate = true) val stockId: String,
    val name: String,
    val price: String,
    val description: String
)

