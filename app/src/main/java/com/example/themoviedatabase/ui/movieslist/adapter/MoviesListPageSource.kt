package com.example.themoviedatabase.ui.movieslist.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.themoviedatabase.models.ApiResponse
import com.example.themoviedatabase.models.MAX_PAGE_SIZE
import com.example.themoviedatabase.models.MoviesListResponse
import com.example.themoviedatabase.repositories.MoviesRepository

class MoviesListPageSource(
    private val moviesRepository: MoviesRepository
) : PagingSource<Int, MoviesListResponse>() {

    override fun getRefreshKey(state: PagingState<Int, MoviesListResponse>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesListResponse> {
        val page: Int = params.key ?: 1
        val pageSize: Int = params.loadSize.coerceAtMost(MAX_PAGE_SIZE)
        return try {
            val response = moviesRepository.getMoviesList(page)
            val moviesList = checkNotNull((response as ApiResponse.Success).data.results)
            val nextKey = if (moviesList.size < pageSize) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(moviesList, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}