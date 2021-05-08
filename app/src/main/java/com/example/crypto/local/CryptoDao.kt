package com.example.crypto.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.crypto.pojo.CryptoDetailItem
import com.example.crypto.pojo.CryptoResponseItem

@Dao
interface CryptoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCrypto(list: List<CryptoResponseItem>)

    @Query("SELECT * FROM crypto_table order by name asc")
    fun getAllCryptoDataBase(): LiveData<List<CryptoResponseItem>>

    @Query("SELECT * FROM crypto_table WHERE id = :id")
    fun getCryptoByID(id: String): LiveData<CryptoResponseItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOneCryptoDetails(detail: List<CryptoDetailItem>)


    @Query("SELECT * FROM  cryptDetail WHERE id=:id")
    fun getOneCryptoDetails(id: String): LiveData<CryptoDetailItem>
}