package com.example.stock.data.model

import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName("userName")
    var username: String,

    @SerializedName("password")
    val password: String,

    )