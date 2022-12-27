package com.example.themoviedatabase.models

data class ResponseData<DATA>(
    var data: DATA,
) {
    object NoDataResponse
}