package com.example.themoviedatabase.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedatabase.models.ApiResponse
import com.example.themoviedatabase.models.DetailsResponse
import com.example.themoviedatabase.repositories.MoviesRepository
import com.example.themoviedatabase.utils.SingleLiveEvent
import com.example.themoviedatabase.utils.extentions.viewModelScopeLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    val loadingStateLiveData: LiveData<Boolean>
        get() = loadingStateSingleLE
    private val loadingStateSingleLE = SingleLiveEvent<Boolean>()

    val errorMessageLiveData: LiveData<ApiResponse.Error>
        get() = errorMessageSingleLE
    private val errorMessageSingleLE = SingleLiveEvent<ApiResponse.Error>()

    val detailsLiveData: LiveData<DetailsResponse>
        get() = detailsSingleLE
    private val detailsSingleLE = SingleLiveEvent<DetailsResponse>()

    fun getMovieDetails(movieId: Int) {
        viewModelScopeLaunch(loadingStateSingleLE, errorMessageSingleLE, detailsSingleLE) {
            moviesRepository.getMovieDetails(movieId)
        }
    }
}