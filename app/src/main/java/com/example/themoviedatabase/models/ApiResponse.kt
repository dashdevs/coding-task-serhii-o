package com.example.themoviedatabase.models

sealed class ApiResponse<out R> {
    companion object {
        val Succeed = Success(Unit)
    }

    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(
        val error: ApiError,
        val title: String = error.title,
        val message: String = error.message
    ) : ApiResponse<Nothing>()
}