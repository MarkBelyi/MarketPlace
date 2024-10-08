package com.example.tikvamarket.DataLayer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tikvamarket.DataLayer.DAO.CartItemDao
import com.example.tikvamarket.DataLayer.DAO.ProductDao
import com.example.tikvamarket.DataLayer.DAO.UserDao
import com.example.tikvamarket.DataLayer.Entitys.CartItem
import com.example.tikvamarket.DataLayer.Entitys.Product
import com.example.tikvamarket.DataLayer.Entitys.User

@Database(
    entities = [
        Product::class,
        CartItem::class,
        User::class
               ],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun cartItemDao(): CartItemDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
