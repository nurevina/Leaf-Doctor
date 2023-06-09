package com.example.leafdoctorapp.data.remote

import android.util.Log
import com.example.leafdoctorapp.data.model.CommonError
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response

sealed interface ApiResult<T : Any> {
    class ApiSuccess<T : Any>(val data: T) : ApiResult<T>
    class ApiError<T : Any>(val code: Int, val message: String?) : ApiResult<T>
}

fun <T : Any, R> ApiResult<T>.fold(
    onSuccess: (T) -> R,
    onError: (CommonError?) -> R
): R = when (this) {
    is ApiResult.ApiSuccess -> onSuccess(data)
    is ApiResult.ApiError -> onError(CommonError(message ?: "Unknown error", code))
}


suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): ApiResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            val str = response.body()
            val json = JSONObject((Gson().toJson(str)))
            Log.d("JSON BODY RAW", json.getString("status"))
            if (json.getString("status") == "success"){
                ApiResult.ApiSuccess(body)
            }else{
                ApiResult.ApiError(code = response.code(), message = json.getString("message"))
            }
        } else {
            ApiResult.ApiError(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        ApiResult.ApiError(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        ApiResult.ApiError(code = 0, message = e.message)
    }
}