package com.example.themoviedatabase.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import com.example.themoviedatabase.base.BaseBindingViewHolder
import com.example.themoviedatabase.databinding.ItemProgressBinding

class ProgressViewHolder(
    binding: ItemProgressBinding
) : BaseBindingViewHolder<LoadState, ItemProgressBinding>(binding) {

    companion object {
        fun from(parent: ViewGroup): ProgressViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemProgressBinding.inflate(layoutInflater, parent, false)
            return ProgressViewHolder(binding)
        }
    }

    override fun bindData(item: LoadState) {}
}