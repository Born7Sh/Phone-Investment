package com.example.stock.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.stock.data.model.Stock
import com.example.stock.data.model.StockEntire

@Dao
interface StockDao {

    @Query("SELECT * FROM Stock")
    fun getAllStock(): List<Stock>

    @Query("SELECT * FROM Stock WHERE stock_classification = 1")
    fun getOwnStock(): List<Stock>

    @Query("SELECT * FROM Stock WHERE stock_classification = 2")
    fun getFavoriteStock(): List<Stock>

    @Query("SELECT * FROM Stock WHERE symbol = :symbol")
    fun getCurrentStock(symbol: String): Stock

    @Query("UPDATE Stock SET price = :price  WHERE symbol = :symbol")
    fun modifyPrice(symbol: String, price: Float)

    @Query("UPDATE Stock SET stock_classification = :classification  WHERE symbol = :symbol")
    fun modifyClassification(symbol: String, classification: Int)
}