package com.example.stock.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.stock.data.model.Stock
import com.example.stock.data.model.StockEntire

@Dao
interface StockEntireDao {

    @Query("SELECT * FROM StockEntire")
    fun getAllStock(): List<StockEntire>

}