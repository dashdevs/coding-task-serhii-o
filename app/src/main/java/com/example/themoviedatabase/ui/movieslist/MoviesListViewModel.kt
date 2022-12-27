package com.example.themoviedatabase.ui.movieslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.themoviedatabase.models.MAX_PAGE_SIZE
import com.example.themoviedatabase.models.MoviesListResponse
import com.example.themoviedatabase.models.PREFETCH_DISTANCE
import com.example.themoviedatabase.repositories.MoviesRepository
import com.example.themoviedatabase.ui.movieslist.adapter.MoviesListPageSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    val requestsLiveData: LiveData<PagingData<MoviesListResponse>> = Pager(PagingConfig(MAX_PAGE_SIZE, PREFETCH_DISTANCE)) {
        MoviesListPageSource(moviesRepository)
    }.liveData.cachedIn(viewModelScope)
}