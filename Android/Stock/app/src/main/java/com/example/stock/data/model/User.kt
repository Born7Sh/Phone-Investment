package com.example.stock.data.model

import com.google.gson.annotations.SerializedName

data class User(
    val key: String,
    val password: String,
    val username: String,
    val account: Int
)