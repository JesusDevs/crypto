package com.example.crypto.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CryptoRetrofit {

    companion object{
        const val BASE_URL = "https://fake-server-app-crypto.herokuapp.com/"
        fun getRetrofitInstance(): ICryptoService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ICryptoService::class.java)
        }
    }
}