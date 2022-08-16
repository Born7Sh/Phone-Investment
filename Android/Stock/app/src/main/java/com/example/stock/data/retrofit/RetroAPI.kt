package com.example.stock.data.retrofit

import com.example.stock.data.Auth
import com.example.stock.data.Stock
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetroAPI {

    @POST("authenticate")
    suspend fun getUserKey(
        @Body auth: Auth?
    ): Response<String>

    @GET("/stock/mylist/{username}")
    suspend fun getMyStockList(
        @Path("username") name: String,
        @Header("Authorization") Authorization: String?
    ): Response<Stock>

}