package com.example.stock.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stock.data.dao.StockEntireDao
import com.example.stock.data.dao.StockFavoriteDao
import com.example.stock.data.dao.StockOwnDao
import com.example.stock.data.model.StockEntire
import com.example.stock.data.model.StockFavorite
import com.example.stock.data.model.StockOwn

@Database(entities = [StockEntire::class, StockFavorite::class, StockOwn::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun stockEntireDao(): StockEntireDao
    abstract fun stockFavoriteDao(): StockFavoriteDao
    abstract fun stockOwnDao(): StockOwnDao
}