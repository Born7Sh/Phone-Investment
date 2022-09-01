package com.example.stock.data.retrofit

import com.example.stock.util.ApiError
import com.example.stock.util.ApiResult
import com.example.stock.util.Success
import com.google.gson.JsonSyntaxException
import okio.IOException
import org.json.JSONObject
import retrofit2.Response

suspend fun <T : Any> handleApi(
    call: suspend () -> Response<T>,
    errorMessage: String = "Some errors occurred, Please try again later"
): ApiResult<T> {
    try {
        val response = call()
        if (response.isSuccessful) {
//            isConnectedToNetwork = true
            response.body()?.let {
                return Success(it)
            }
        }
        response.errorBody()?.let {
            try {
                val errorString  = it.string()
                val errorObject = JSONObject(errorString)
                return ApiError(
                    RuntimeException(if(errorObject.has("message")) errorObject.getString("message") else "Error occurred, Try again Later"))
            } catch (ignored: JsonSyntaxException) {
                return ApiError(RuntimeException(errorMessage))
            }
        }
        return ApiError(RuntimeException(errorMessage))
    } catch (e: Exception) {
        if (e is IOException) {
//            isConnectedToNetwork = false
        }
        return ApiError(RuntimeException(errorMessage))
    }
}