package com.example.stock.data.retrofit

import com.example.stock.data.Auth
import retrofit2.create

class RetroAPIRepository {
    private val retro = GlobalApplication.baseService.create(RetroAPI::class.java)

    //    suspend fun getUserKey(auth : Auth) = retro.getUserKey(auth)
    suspend fun getUserKey(auth: Auth) = retro.getUserKey(auth)

    suspend fun getMyStockList(username: String, key: String) = retro.getMyStockList(username, key)

    suspend fun getMyMoney(username: String, key: String) = retro.getMyMoney(username, key)

    suspend fun getStockPrice(SYMBOL: String, key: String) = retro.getStockPrice(SYMBOL, key)
}