package com.example.themoviedatabase.core


import com.example.themoviedatabase.BuildConfig
import com.example.themoviedatabase.models.ApiError
import com.example.themoviedatabase.models.ApiResponse
import com.google.gson.Gson
import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface ServiceErrorParser {
    val gson: Gson

    fun parseError(e: Exception): ApiResponse<Nothing> {
        if (!BuildConfig.DEBUG) {
            Timber.e(e)
        }

        return when (e) {
            is HttpException -> e.response()?.errorBody()?.charStream()?.let {
                try {
                    gson.fromJson(it, ApiResponse.Error::class.java)
                } catch (e: Exception) {
                    null
                }
            } ?: ApiResponse.Error(ApiError(message = "Unknown error", cause = e))
            is UnknownHostException, is SocketTimeoutException -> ApiResponse.Error(ApiError.NetworkConnectionError(e))
            is ApiError -> ApiResponse.Error(e)
            else -> ApiResponse.Error(ApiError(message = "Unknown error", cause = e))
        }
    }
}