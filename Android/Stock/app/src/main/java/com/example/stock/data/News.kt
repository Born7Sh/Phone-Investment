package com.example.stock.data

import androidx.room.PrimaryKey

data class News(
    @PrimaryKey(autoGenerate = true) val newsId: String,
    val title: String,
    val date: String,
    val article: String,
    val img: String
)