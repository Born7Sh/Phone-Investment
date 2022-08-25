package com.example.stock.data.model

data class CandleStock(
    var createdAt: Long = 0,
    val open: Float,
    val close: Float,
    val shadowHigh: Float,
    val shadowLow: Float
)