package com.example.stock.util

sealed class ApiResult<out T : Any?>

data class Success<out T : Any?>(val data: T) : ApiResult<T>()

data class ApiError(val exception: Exception) : ApiResult<Nothing>()

data class ExceptionError(val exception: Exception) : ApiResult<Nothing>()