package com.example.stock.data.repository

import com.example.stock.data.model.Auth
import com.example.stock.data.model.Stock
import com.example.stock.global.GlobalApplication
import com.example.stock.data.retrofit.RetroAPI

class StockRepository {
    // room 영역
    // Room Entity 조절용 변수선언
    private val ownInstance = GlobalApplication.appDataBaseInstance.stockOwnDao()
    private val favoriteInstance = GlobalApplication.appDataBaseInstance.stockFavoriteDao()
    private val entireInstance = GlobalApplication.appDataBaseInstance.stockEntireDao()

    // 내가 보유한 것 쿼리
    suspend fun insertOwnStock(stock: Stock) = ownInstance.insertStock(stock)
    suspend fun deleteOwnStockSymbol(symbol: String) = ownInstance.deleteStockSymbol(symbol)
    suspend fun getAllOwnStock() = ownInstance.getAllStock()
    suspend fun modifyOwnStock(symbol: String, price: Float) = ownInstance.modifyStock(symbol, price)

    // 내가 관심있는거 쿼리
    suspend fun insertFavoriteStock(stock: Stock) = favoriteInstance.insertStock(stock)
    suspend fun deleteFavoriteStockSymbol(symbol: String) = favoriteInstance.deleteStockSymbol(symbol)
    suspend fun getAllFavoriteStock() = favoriteInstance.getAllStock()
    suspend fun modifyFavoriteStock(symbol: String, price: Float) = favoriteInstance.modifyStock(symbol, price)

    // 모든 기업 쿼리
    suspend fun getAllStock() = entireInstance.getAllStock()

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 여기서부터 retroFit 영역
    private val retro = GlobalApplication.baseService.create(RetroAPI::class.java)

    suspend fun getUserKey(auth: Auth) = retro.getUserKey(auth)

    suspend fun getMyStockList(username: String, key: String) = retro.getMyStockList(username, key)

    suspend fun getMyMoney(username: String, key: String) = retro.getMyMoney(username, key)

    suspend fun getStockPrice(SYMBOL: String, key: String) = retro.getStockPrice(SYMBOL, key)
}