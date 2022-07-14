package com.example.stock.data.retrofit

import com.example.stock.data.Auth
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST




interface RetroAPI {

    @POST("/authenticate")
    fun getUserKey(
        @Body ld: Auth?
    ): Call<String>

}