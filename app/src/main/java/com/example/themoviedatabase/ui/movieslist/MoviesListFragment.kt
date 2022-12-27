package com.example.themoviedatabase.ui.movieslist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.themoviedatabase.R
import com.example.themoviedatabase.databinding.FragmentMoviesListBinding
import com.example.themoviedatabase.ui.common.LoaderStateAdapter
import com.example.themoviedatabase.ui.movieslist.adapter.MoviesListAdapter
import com.example.themoviedatabase.utils.extentions.viewBindingWithBinder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    private val binding: FragmentMoviesListBinding by viewBindingWithBinder(FragmentMoviesListBinding::bind)
    private val viewModel: MoviesListViewModel by viewModels()
    private var adapter: MoviesListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMoviesListAdapter()
        subscribe()
    }

    private fun setupMoviesListAdapter() {
        adapter = MoviesListAdapter { item ->
            onItemClick(item.id)
        }
        adapter?.addLoadStateListener { state ->
            binding.progressBar.isVisible = state.refresh == LoadState.Loading
        }
        binding.rvMoviesList.adapter = adapter?.withLoadStateHeaderAndFooter(
            header = LoaderStateAdapter(),
            footer = LoaderStateAdapter()
        )
    }

    private fun subscribe() {
        viewModel.requestsLiveData.observe(viewLifecycleOwner) { moviesList ->
            adapter?.submitData(viewLifecycleOwner.lifecycle, moviesList)
        }
    }

    private fun onItemClick(movieId: Int) {
        val action = MoviesListFragmentDirections.actionMoviesListFragmentToDetailsFragment(movieId)
        findNavController().navigate(action)
    }
}