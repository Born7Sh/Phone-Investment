package com.example.stock.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.stock.data.model.Stock

@Dao
interface StockOwnDao {
    @Insert
    suspend fun insertStock(stock: Stock): String

    @Query("DELETE FROM Stock Where symbol = :symbol")
    suspend fun deleteStockSymbol(symbol: String)

    @Query("SELECT * FROM stock")
    suspend fun getAllStock(): List<Stock>

    @Query("UPDATE Stock SET price = :price  WHERE symbol = :symbol")
    suspend fun modifyStock(symbol: String, price: Float)
}

@Dao
interface StockFavoriteDao {
    @Insert
    suspend fun insertStock(stock: Stock): String

    @Query("DELETE FROM Stock Where symbol = :symbol")
    suspend fun deleteStockSymbol(symbol: String)

    @Query("SELECT * FROM stock")
    suspend fun getAllStock(): List<Stock>

    @Query("UPDATE Stock SET price = :price  WHERE symbol = :symbol")
    suspend fun modifyStock(symbol: String, price: Float)
}

@Dao
interface StockEntireDao {

    @Query("SELECT * FROM stock")
    suspend fun getAllStock(): List<Stock>
}