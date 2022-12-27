package com.example.themoviedatabase.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.themoviedatabase.R
import com.example.themoviedatabase.databinding.FragmentDetailsBinding
import com.example.themoviedatabase.utils.extentions.viewBindingWithBinder
import com.example.themoviedatabase.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val binding: FragmentDetailsBinding by viewBindingWithBinder(FragmentDetailsBinding::bind)
    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()
    private val movieId: Int get() = args.movieId

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()
        viewModel.getMovieDetails(movieId)
    }

    private fun subscribe() {
        viewModel.loadingStateLiveData.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }
        viewModel.errorMessageLiveData.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
        }
        viewModel.detailsLiveData.observe(viewLifecycleOwner) { details ->
            loadImage(binding.ivPoster, "https://image.tmdb.org/t/p/w500" + details.posterPath)
            binding.tvTitle.text = details.title
            binding.tvTagline.text = details.tagline
            binding.tvReleaseDate.text = details.releaseDate
            binding.tvDescription.text = details.overview
        }
    }
}