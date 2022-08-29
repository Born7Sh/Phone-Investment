package com.example.stock.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.stock.data.dao.StockDao
import com.example.stock.data.dao.StockEntireDao
import com.example.stock.data.dao.StockFavoriteDao
import com.example.stock.data.dao.StockOwnDao
import com.example.stock.data.model.Stock
import com.example.stock.data.model.StockEntire
import com.example.stock.data.model.StockFavorite
import com.example.stock.data.model.StockOwn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Stock::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    //    abstract fun stockEntireDao(): StockEntireDao
    //    abstract fun stockFavoriteDao(): StockFavoriteDao
    //    abstract fun stockOwnDao(): StockOwnDao
    abstract fun stockDao(): StockDao
}