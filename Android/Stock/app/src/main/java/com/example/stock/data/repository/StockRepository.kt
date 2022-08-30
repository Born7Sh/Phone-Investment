package com.example.stock.data.repository

import com.example.stock.data.model.Auth
import com.example.stock.data.model.Stock
import com.example.stock.global.GlobalApplication
import com.example.stock.data.retrofit.RetroAPI

class StockRepository {
    // room 영역
    // Room Entity 조절용 변수선언

    private val stockInstance = GlobalApplication.appDataBaseInstance.stockDao()

    fun getOwnStock() = stockInstance.getOwnStock()

    fun getAllStock() = stockInstance.getAllStock()

    fun getFavoriteStock() = stockInstance.getFavoriteStock()

    fun modifyClassification(symbol: String, classification: Int) = stockInstance.modifyClassification(symbol, classification)

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 여기서부터 retroFit 영역
    private val retro = GlobalApplication.baseService.create(RetroAPI::class.java)

    suspend fun getUserKey(auth: Auth) = retro.getUserKey(auth)

    suspend fun getMyStockList(username: String, key: String) = retro.getMyStockList(username, key)

    suspend fun getMyMoney(username: String, key: String) = retro.getMyMoney(username, key)

    suspend fun getStockPrice(SYMBOL: String, key: String) = retro.getStockPrice(SYMBOL, key)
}