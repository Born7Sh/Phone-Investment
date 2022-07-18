package com.example.stock.data.retrofit

import com.example.stock.data.Auth
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST




interface RetroAPI {

    @POST("/authenticate")
    fun getUserKey(
        @Body auth: Auth?
    ): Call<String>

    @POST("/signup")
    fun setSignUp(
        @Body auth: Auth?
    ): Call<Auth>
}