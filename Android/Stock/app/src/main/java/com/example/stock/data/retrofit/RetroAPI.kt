package com.example.stock.data.retrofit

import com.example.stock.data.model.Auth
import com.example.stock.data.model.Deal
import com.example.stock.data.model.Stock
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

    @GET("/stock/stocklist/{username}")
    suspend fun getStockList(
        @Path("username") name: String,
        @Header("Authorization") Authorization: String?
    ): Response<List<Stock>>

    @GET("/stock/mymoney/{username}")
    suspend fun getMyMoney(
        @Path("username") name: String,
        @Header("Authorization") Authorization: String?
    ): Response<Float>


    // 데이터 받기
    @POST("/stock/{SYMBOL}")
    suspend fun getStockPrice(
        @Path("SYMBOL") SYMBOL: String,
        @Header("Authorization") Authorization: String?
    ): Response<String>


    // 팔기
    @POST("/stock/buy")
    suspend fun buyRequest(
        @Body deal: Deal?,
        @Header("Authorization") Authorization: String?
    ): Response<String>

    // 사기
    @POST("/stock/sell")
    suspend fun sellRequest(
        @Body deal: Deal?,
        @Header("Authorization") Authorization: String?
    ): Response<String>

}