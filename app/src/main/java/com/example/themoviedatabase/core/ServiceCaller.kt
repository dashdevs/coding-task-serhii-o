package com.example.themoviedatabase.core

import com.example.themoviedatabase.models.ApiResponse
import com.example.themoviedatabase.models.ResponseData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface ServiceCaller : ServiceErrorParser {
    val ioDispatcher: CoroutineDispatcher

    suspend fun <T> execute(method: suspend () -> T): ApiResponse<T> {
        return withContext(ioDispatcher) {
            try {
                ApiResponse.Success(method())
            } catch (e: Exception) {
                parseError(e)
            }
        }
    }

    suspend fun <T> call(method: suspend () -> ApiResponse<T>): ApiResponse<T> {
        return withContext(ioDispatcher) {
            try {
                method()
            } catch (e: Exception) {
                parseError(e)
            }
        }
    }

    suspend fun <T> mapCall(method: suspend () -> ResponseData<T>): ApiResponse<T> {
        return withContext(ioDispatcher) {
            try {
                val response = method()
                ApiResponse.Success(response.data)
            } catch (e: Exception) {
                parseError(e)
            }
        }
    }

    suspend fun <T> discardResultCall(method: suspend () -> T): ApiResponse<Unit> {
        return withContext(ioDispatcher) {
            try {
                method()
                ApiResponse.Success(Unit)
            } catch (e: Exception) {
                parseError(e)
            }
        }
    }
}