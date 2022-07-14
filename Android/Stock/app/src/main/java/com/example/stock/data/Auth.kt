package com.example.stock.data

import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String,
)