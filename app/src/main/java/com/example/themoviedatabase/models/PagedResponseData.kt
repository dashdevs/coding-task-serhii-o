package com.example.themoviedatabase.models

import com.google.gson.annotations.SerializedName


const val MAX_PAGE_SIZE: Int = 20
const val PREFETCH_DISTANCE: Int = 15

data class PagedResponseData<DATA>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<DATA>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)