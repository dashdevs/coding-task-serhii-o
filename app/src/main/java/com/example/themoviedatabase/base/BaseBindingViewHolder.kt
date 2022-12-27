package com.example.themoviedatabase.base

import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.themoviedatabase.utils.extentions.onClick


abstract class BaseBindingViewHolder<in D, VB : ViewBinding>(
    protected val binding: VB,
    onItemClick: ((position: Int) -> Unit)? = null
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.onClick {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                onItemClick?.invoke(bindingAdapterPosition)
            }
        }
    }

    abstract fun bindData(item: D)

    fun withBinding(block: (VB.() -> Unit)) {
        binding.apply {
            block.invoke(this)
        }
    }

    fun getString(@StringRes id: Int): String = binding.root.context.getString(id)
}