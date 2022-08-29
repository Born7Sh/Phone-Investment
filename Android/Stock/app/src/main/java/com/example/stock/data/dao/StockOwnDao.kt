package com.example.stock.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.stock.data.model.Stock
import com.example.stock.data.model.StockOwn

@Dao
interface StockOwnDao {
    @Insert
    fun insertStock(stock: StockOwn)

//    @Delete
//    fun deleteStock(stock: Stock)
//
//    @Query("DELETE FROM StockOwn Where symbol = :symbol")
//    suspend fun deleteStockBySymbol(symbol: String)

    @Query("SELECT * FROM StockOwn")
    fun getAllStock(): List<StockOwn>

    @Query("UPDATE StockOwn SET price = :price  WHERE symbol = :symbol")
    fun modifyStock(symbol: String, price: Float)
}

