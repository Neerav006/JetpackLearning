package com.example.myapplication.data.remote

import com.example.myapplication.data.model.CoinInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/v1/coins")
    suspend fun getCurrencyList(): List<CoinDtoX>

    @GET("/v1/coins/{id}")
    suspend fun getCurrencyDetail(@Path("id") id:String): CoinInfo

}