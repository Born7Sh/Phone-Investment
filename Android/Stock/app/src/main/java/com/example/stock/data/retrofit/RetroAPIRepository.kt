package com.example.stock.data.retrofit

import retrofit2.create

class RetroAPIRepository {
    private val retro = GlobalApplication.baseService.create(RetroAPI::class.java)

    fun getMyStockList(username: String, key: String) = retro.getMyStockList(username, key)

//    suspend fun getStockSPY(key : String) = retro.getStockSPY(key)
}