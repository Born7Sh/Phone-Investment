package com.example.stock.data

object DataUtilBar {
    fun getCandleStockData(): List<CandleStock> {
        return listOf(
            CandleStock(
                createdAt = 0,
                open = 222.8F,
                close = 222.9F,
                shadowHigh = 224.0F,
                shadowLow = 222.2F
            ),
            CandleStock(
                createdAt = 1,
                open = 222.0F,
                close = 222.2F,
                shadowHigh = 222.4F,
                shadowLow = 222.0F
            ),
            CandleStock(
                createdAt = 2,
                open = 222.2F,
                close = 221.9F,
                shadowHigh = 222.5F,
                shadowLow = 221.5F
            ),
            CandleStock(
                createdAt = 3,
                open = 222.4F,
                close = 222.3F,
                shadowHigh = 223.7F,
                shadowLow = 222.1F
            ),
            CandleStock(
                createdAt = 4,
                open = 221.6F,
                close = 221.9F,
                shadowHigh = 221.9F,
                shadowLow = 221.5F
            ),
            CandleStock(
                createdAt = 5,
                open = 221.8F,
                close = 224.9F,
                shadowHigh = 225.0F,
                shadowLow = 221.0F
            ),
            CandleStock(
                createdAt = 6,
                open = 225.0F,
                close = 220.2F,
                shadowHigh = 225.4F,
                shadowLow = 219.2F
            ),
            CandleStock(
                createdAt = 7,
                open = 222.2F,
                close = 225.9F,
                shadowHigh = 227.5F,
                shadowLow = 222.2F
            ),
            CandleStock(
                createdAt = 8,
                open = 226.0F,
                close = 228.1F,
                shadowHigh = 228.1F,
                shadowLow = 225.1F
            ),
            CandleStock(
                createdAt = 9,
                open = 227.6F,
                close = 228.9F,
                shadowHigh = 230.9F,
                shadowLow = 226.5F
            ),
            CandleStock(
                createdAt = 10,
                open = 228.6F,
                close = 228.6F,
                shadowHigh = 230.9F,
                shadowLow = 228.0F
            )
        )
    }
}