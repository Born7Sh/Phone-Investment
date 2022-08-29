package com.example.stock.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.stock.data.model.Stock
import com.example.stock.data.model.StockFavorite

@Dao
interface StockFavoriteDao {
    @Insert
    fun insertStock(stock: StockFavorite)

//    @Query("DELETE FROM StockFavorite Where symbol = :symbol")
//    suspend fun deleteStockSymbol(symbol: String)

    @Query("SELECT * FROM StockFavorite")
    fun getAllStock(): List<StockFavorite>

    @Query("UPDATE StockFavorite SET price = :price  WHERE symbol = :symbol")
    fun modifyStock(symbol: String, price: Float)
}