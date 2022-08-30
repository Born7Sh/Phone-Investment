package com.example.stock.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stock.data.dao.StockDao
import com.example.stock.data.model.Stock

@Database(entities = [Stock::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun stockDao(): StockDao
}