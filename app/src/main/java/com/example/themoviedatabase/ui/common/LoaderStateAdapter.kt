package com.example.themoviedatabase.ui.common

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.themoviedatabase.base.BaseBindingViewHolder

class LoaderStateAdapter : LoadStateAdapter<BaseBindingViewHolder<LoadState, *>>() {

    private companion object {
        private const val ERROR_TYPE: Int = 1
        private const val PROGRESS_TYPE: Int = 0
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder<LoadState, *>, loadState: LoadState) {
        holder.bindData(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): BaseBindingViewHolder<LoadState, *> {
        return when (loadState) {
            LoadState.Loading -> ProgressViewHolder.from(parent)
            is LoadState.Error -> ErrorViewHolder.from(parent)
            is LoadState.NotLoading -> error("Not supported")
        }
    }

    override fun getStateViewType(loadState: LoadState): Int = when (loadState) {
        LoadState.Loading -> PROGRESS_TYPE
        is LoadState.Error -> ERROR_TYPE
        is LoadState.NotLoading -> error("Not supported")
    }
}