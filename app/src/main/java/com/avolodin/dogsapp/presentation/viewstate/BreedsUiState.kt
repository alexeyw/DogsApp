package com.avolodin.dogsapp.presentation.viewstate

import com.avolodin.dogsapp.ui.model.BreedView

sealed class BreedsUiState {
    object Loading : BreedsUiState()
    data class Error(val errorMessage: String) : BreedsUiState()
    data class Success(val breeds: List<BreedView>) : BreedsUiState()
}