package com.example.stock.data.retrofit

import retrofit2.create

class RetroAPIRepository {
    private val retro = GlobalApplication.baseService.create(RetroAPI::class.java)

    suspend fun getMyStockList = retro.getMyStockList()
}