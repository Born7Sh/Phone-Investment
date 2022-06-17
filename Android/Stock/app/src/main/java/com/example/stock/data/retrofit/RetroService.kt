package com.example.stock.data.retrofit

import android.telecom.Call
import com.example.stock.data.Stock
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface RetroService {

    @GET("users/HYE0N1127")
    fun getUserData(@Path("email") email : String, @Header("Authorization") auth : String): Call<Stock>
}