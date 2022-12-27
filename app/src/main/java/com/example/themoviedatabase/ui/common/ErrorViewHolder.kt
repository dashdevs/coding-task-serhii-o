package com.example.themoviedatabase.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import com.example.themoviedatabase.base.BaseBindingViewHolder
import com.example.themoviedatabase.databinding.ItemErrorBinding

class ErrorViewHolder(
    binding: ItemErrorBinding
) : BaseBindingViewHolder<LoadState, ItemErrorBinding>(binding) {

    companion object {
        fun from(parent: ViewGroup): ErrorViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemErrorBinding.inflate(layoutInflater, parent, false)
            return ErrorViewHolder(binding)
        }
    }

    override fun bindData(item: LoadState) = withBinding {
        require(item is LoadState.Error)
        tvErrorMessage.text = item.error.localizedMessage
    }
}