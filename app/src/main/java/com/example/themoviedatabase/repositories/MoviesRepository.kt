package com.example.themoviedatabase.repositories

import com.example.themoviedatabase.core.ServiceCaller
import com.example.themoviedatabase.models.ApiResponse
import com.example.themoviedatabase.models.DetailsResponse
import com.example.themoviedatabase.models.MoviesListResponse
import com.example.themoviedatabase.models.PagedResponseData
import com.example.themoviedatabase.services.MoviesService
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    override val ioDispatcher: CoroutineDispatcher,
    override val gson: Gson,
    private val moviesService: MoviesService
) : ServiceCaller {

    suspend fun getMoviesList(page: Int): ApiResponse<PagedResponseData<MoviesListResponse>> = execute {
        moviesService.getMoviesList("c9856d0cb57c3f14bf75bdc6c063b8f3", page)
    }

    suspend fun getMovieDetails(movieId: Int): ApiResponse<DetailsResponse> = execute {
        moviesService.getMovieDetails(movieId, "c9856d0cb57c3f14bf75bdc6c063b8f3")
    }
}