package com.example.themoviedatabase.ui.movieslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedatabase.base.BaseBindingViewHolder
import com.example.themoviedatabase.databinding.ItemMovieBinding
import com.example.themoviedatabase.models.MoviesListResponse
import com.example.themoviedatabase.utils.extentions.onClick
import com.example.themoviedatabase.utils.loadImage

class MoviesListViewHolder(
    binding: ItemMovieBinding,
    private val onClick: (position: Int) -> Unit
) : BaseBindingViewHolder<MoviesListResponse, ItemMovieBinding>(binding) {

    companion object {
        fun from(parent: ViewGroup, onClick: (position: Int) -> Unit): MoviesListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val biding = ItemMovieBinding.inflate(layoutInflater, parent, false)
            return MoviesListViewHolder(biding, onClick)
        }
    }

    init {
        binding.root.onClick {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                onClick.invoke(bindingAdapterPosition)
            }
        }
    }

    override fun bindData(item: MoviesListResponse) = withBinding {
        root.context.loadImage(ivPoster, "https://image.tmdb.org/t/p/w500" + item.posterPath)
        tvTitle.text = item.title
        tvReleaseDate.text = item.releaseDate
        tvDescription.text = item.overview
    }
}