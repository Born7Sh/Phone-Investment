package com.example.stock.data


data class Company (
    val cid : String,
    val name : String,
    val description : String, // 설명
    val market_capital : Int, // 시가 총액
    val volume : Int,
    val max_price : Int, // 최고가
    val min_price : Int, // 최소가
    //val News : ArrayList<News>,
        )