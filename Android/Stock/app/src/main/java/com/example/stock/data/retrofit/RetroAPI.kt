package com.example.stock.data.retrofit

import com.example.stock.data.Auth
import com.example.stock.data.Stock
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetroAPI {

    @POST("authenticate")
    fun getUserKey(
        @Body auth: Auth?
    ): Call<String>

    @POST("/signup")
    fun setSignUp(
        @Body auth: Auth?
    ): Call<Auth>

    @GET("/stock/mylist/{username}")
    fun getMyStockList(
        @Path("username") name: String,
//        @Header("Authorization") key: String
        @Header("Authorization") Authorization: String?

    ): Call<Stock>

    @GET("stock/mymoney/{username}")
    fun getMyMoney(
        @Header("Authorization") Authorization: String?,
        @Path("username") name: String
//        @Header("Authorization") key: String
    ): Call<Stock>

    @GET("/stock/stocklist")
    fun getStocklist(
        @Header("Authorization") Authorization: String?
    )

//    @GET("stock/mylist/{username}")
//    fun getMyStockList(
//        @Path("username") name: String,
//        @Header("Authorization") key: String
//    ): Call<Stock>

//    @GET("stock/SPY")
//    suspend fun getMyStockList(
//        @Header ("authorization") key: String?
//    ) :Response<Stock>

//    @GET("stock/SPY")
//    suspend fun getStockSPY(
//        @Header ("authorization") key: String?
//    ) :Response<Stock>

}