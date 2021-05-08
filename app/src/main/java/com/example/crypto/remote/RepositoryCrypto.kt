package com.example.crypto.remote

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.crypto.local.CryptoDao
import com.example.crypto.pojo.CryptoResponseItem

class RepositoryCrypto (private val dao : CryptoDao){

    val liveDataCryptoDB : LiveData<List<CryptoResponseItem>> = dao.getAllCryptoDataBase()

    suspend fun getGameWithCoroutines()  {
        Log.d("REPOSITORY", "UTILIZANDO COROUTINES")
        try {
            val response = CryptoRetrofit.getRetrofitInstance().getCrypto()

            when(response.isSuccessful) {

                true -> response.body()?.let {
                    //Aca se esta insertando en la Base de datos listado de Crypto
                    Log.d("repo1", "${it}")
                    dao.insertAllCrypto(it)
                    Log.d("repo", "${it}")
                }
                false -> Log.d("ERROR", " ${response.code()} : ${response.errorBody()} ")
            }
        } catch (t: Throwable){
            Log.e("ERROR CORUTINA", t.message.toString())
        }
    }
}

