package com.example.crypto.remote

import com.example.crypto.pojo.CryptoResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ICryptoService {

    @GET("general/")
    suspend fun getCrypto(): Response<List<CryptoResponseItem>>



}