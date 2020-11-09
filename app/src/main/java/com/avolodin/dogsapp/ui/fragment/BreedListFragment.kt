package com.avolodin.dogsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.avolodin.dogsapp.databinding.FragmentBreedListBinding
import com.avolodin.dogsapp.presentation.BreedListViewModel
import com.avolodin.dogsapp.presentation.viewstate.BreedsUiState
import com.avolodin.dogsapp.presentation.viewstate.BreedsUiState.*
import com.avolodin.dogsapp.ui.adapter.BreedListAdapter
import com.avolodin.dogsapp.ui.adapter.OnBreedItemClickListener
import dagger.android.support.DaggerFragment
import javax.inject.Inject

open class BreedListFragment : DaggerFragment(), OnBreedItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<BreedListViewModel> { viewModelFactory }
    private var _binding: FragmentBreedListBinding? = null
    private val binding get() = _binding!!
    private lateinit var listAdapter: BreedListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBreedListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listAdapter = BreedListAdapter(this)
        binding.breedList.adapter = listAdapter
        viewModel.apply {
            viewStateBreeds.observe(viewLifecycleOwner, { observeViewState(it) })
            loadBreedsList()
        }
    }

    private fun observeViewState(viewState: BreedsUiState) {
        when (viewState) {
            is Loading -> {
                binding.listLoadingProgress.visibility = View.VISIBLE
            }
            is Error -> {
                binding.listLoadingProgress.visibility = View.GONE
                context?.let {
                    Toast.makeText(it, viewState.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
            is Success -> {
                binding.listLoadingProgress.visibility = View.GONE
                viewState.breeds.let { listAdapter.submitList(it) }
            }
        }
    }

    override fun onItemClicked(breed: String?) {
        let {
            val action =
                BreedListFragmentDirections.actionBreedListFragmentToBreedImageListFragment(breed!!)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}