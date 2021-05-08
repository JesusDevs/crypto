package com.example.crypto.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crypto.pojo.CryptoDetailItem
import com.example.crypto.pojo.CryptoResponseItem

@Database(entities = [CryptoResponseItem::class , CryptoDetailItem::class],version = 1)
abstract class CryptoDataBase:RoomDatabase() {

    abstract fun getCryptoDao( ):CryptoDao

    companion object {
        @Volatile
        private var INSTANCE : CryptoDataBase? = null

        fun getDataBase(context: Context): CryptoDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    CryptoDataBase::class.java,
                    "cryptoDao")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}