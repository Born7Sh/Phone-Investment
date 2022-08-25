package com.example.stock.data.model

import com.google.gson.annotations.SerializedName

data class Deal(
    @SerializedName("symbol")
    val symbol : String,
    @SerializedName("num")
    val num : Int,
    @SerializedName("price")
    val price : Float,
    @SerializedName("username")
    val username : String

)

