package com.example.themoviedatabase.services

import com.example.themoviedatabase.models.DetailsResponse
import com.example.themoviedatabase.models.MoviesListResponse
import com.example.themoviedatabase.models.PagedResponseData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("discover/movie")
    suspend fun getMoviesList(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): PagedResponseData<MoviesListResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): DetailsResponse
}