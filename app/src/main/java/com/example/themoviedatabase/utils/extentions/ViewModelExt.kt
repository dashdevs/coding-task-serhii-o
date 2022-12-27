package com.example.themoviedatabase.utils.extentions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedatabase.models.ApiResponse
import com.example.themoviedatabase.utils.SingleLiveEvent
import kotlinx.coroutines.launch

fun <T> ViewModel.viewModelScopeLaunch(
    loading: SingleLiveEvent<Boolean>? = null,
    error: SingleLiveEvent<ApiResponse.Error>,
    method: suspend () -> ApiResponse<T>,
    onSuccess: suspend (T) -> Unit
) {
    loading?.value = true
    viewModelScope.launch {
        val response = try {
            method()
        } finally {
            loading?.value = false
        }
        when (response) {
            is ApiResponse.Success -> onSuccess(response.data)
            is ApiResponse.Error -> error.postValue(response)
        }
    }
}

fun <T> ViewModel.viewModelScopeLaunch(
    loading: SingleLiveEvent<Boolean>? = null,
    error: SingleLiveEvent<ApiResponse.Error>,
    success: MutableLiveData<T>,
    method: suspend () -> ApiResponse<T>
) {
    viewModelScopeLaunch(loading, error, method) { success.postValue(it) }
}

fun <T> ViewModel.viewModelScopeLaunchDiscardingResult(
    loading: SingleLiveEvent<Boolean>? = null,
    error: SingleLiveEvent<ApiResponse.Error>,
    success: SingleLiveEvent<Unit>?,
    method: suspend () -> ApiResponse<T>
) {
    viewModelScopeLaunch(loading, error, method) { success?.call() }
}