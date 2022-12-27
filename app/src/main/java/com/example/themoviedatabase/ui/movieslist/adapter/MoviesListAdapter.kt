package com.example.themoviedatabase.ui.movieslist.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.themoviedatabase.models.MoviesListResponse

class MoviesListAdapter(
    private val itemClickFun: (position: MoviesListResponse) -> Unit
) : PagingDataAdapter<MoviesListResponse, MoviesListViewHolder>(DiffCallback()) {

    override fun onBindViewHolder(holder: MoviesListViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bindData(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesListViewHolder {
        return MoviesListViewHolder.from(parent) { position ->
            getItem(position)?.let(itemClickFun)
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<MoviesListResponse>() {
    override fun areItemsTheSame(oldItem: MoviesListResponse, newItem: MoviesListResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MoviesListResponse, newItem: MoviesListResponse): Boolean {
        return oldItem.id == newItem.id
                && oldItem.originalTitle == newItem.originalTitle
                && oldItem.overview == newItem.overview
                && oldItem.popularity == newItem.popularity
                && oldItem.posterPath == newItem.posterPath
                && oldItem.releaseDate == newItem.releaseDate
                && oldItem.title == newItem.title
                && oldItem.voteAverage == newItem.voteAverage
                && oldItem.voteCount == newItem.voteCount
    }
}