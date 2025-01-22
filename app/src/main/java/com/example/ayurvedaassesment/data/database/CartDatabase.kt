package com.example.ayurvedaassesment.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ayurvedaassesment.data.models.SingleCartItem


@Database(
    entities = [SingleCartItem::class],
    version = 1,
    exportSchema = false
)
abstract class CartDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao

    companion object {

        @Volatile
        private var instance: CartDatabase? = null
        private val LOCK = Any()

        // singleton pattern
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            val INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                CartDatabase::class.java,
                "cart_database.db"
            )
                .build()
            instance = INSTANCE

            instance
        }

    }

}
