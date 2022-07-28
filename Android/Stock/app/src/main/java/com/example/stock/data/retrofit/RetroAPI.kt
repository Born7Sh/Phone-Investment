package com.example.stock.data.retrofit

import com.example.stock.data.Auth
import com.example.stock.data.Stock
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetroAPI {

    @POST("/authenticate")
    fun getUserKey(
        @Body auth: Auth?
    ): Call<String>

    @POST("/signup")
    fun setSignUp(
        @Body auth: Auth?
    ): Call<Auth>

    @GET("/mylist")
    suspend fun getMyStockList(
        @Header ("Authorization") auth: Auth?
    ) :Response<Stock>
}