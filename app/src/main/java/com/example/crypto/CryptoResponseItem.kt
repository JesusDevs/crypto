package com.example.crypto


import com.google.gson.annotations.SerializedName

data class CryptoResponseItem(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("logo_url")
    val logoUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("price_date")
    val priceDate: String,
    @SerializedName("price_timestamp")
    val priceTimestamp: String,
    @SerializedName("rank")
    val rank: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("symbol")
    val symbol: String
)