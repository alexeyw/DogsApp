package com.avolodin.dogsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.avolodin.dogsapp.databinding.FragmentBreedImageListBinding
import com.avolodin.dogsapp.presentation.BreedImageListViewModel
import com.avolodin.dogsapp.presentation.viewstate.BreedImagesUiState
import com.avolodin.dogsapp.ui.adapter.BreedImageListAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class BreedImageListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var listAdapter: BreedImageListAdapter

    private val viewModel by viewModels<BreedImageListViewModel> { viewModelFactory }
    private var _binding: FragmentBreedImageListBinding? = null
    private val binding get() = _binding!!
    private val args: BreedImageListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBreedImageListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.imageList.adapter = listAdapter
        viewModel.apply {
            viewStateBreedImages.observe(viewLifecycleOwner, { observeViewState(it) })
            loadBreedImageList(args.breed)
        }
    }

    private fun observeViewState(viewState: BreedImagesUiState) {
        when (viewState) {
            is BreedImagesUiState.Loading -> {
                binding.listLoadingProgress.visibility = View.VISIBLE
            }
            is BreedImagesUiState.Error -> {
                binding.listLoadingProgress.visibility = View.GONE
                context?.let {
                    Toast.makeText(it, viewState.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
            is BreedImagesUiState.Success -> {
                binding.listLoadingProgress.visibility = View.GONE
                viewState.images.let { listAdapter.submitList(it) }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}