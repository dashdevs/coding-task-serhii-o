package com.example.themoviedatabase.models

open class ApiError(
    val title: String = "Error",
    message: String,
    private val extras: List<Extra> = listOf(),
    override val cause: Throwable? = null
) : Exception(message) {

    override val message: String get() = super.message ?: ""

    val fields: List<String> get() = extras.map { it.field }
    val keys: List<String> get() = extras.flatMap { it.keyMessageMap.keys }

    data class Extra(
        val field: String,
        val keyMessageMap: Map<String, String>
    )

    class NetworkConnectionError(cause: Throwable? = null) : ApiError(message = "Please check your internet connection and try again later.", cause = cause)
}