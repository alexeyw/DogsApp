package com.avolodin.dogsapp.presentation.viewstate

import com.avolodin.dogsapp.ui.model.ImageView

sealed class BreedImagesUiState {
    object Loading : BreedImagesUiState()
    data class Error(val errorMessage: String) : BreedImagesUiState()
    data class Success(val images: List<ImageView>) : BreedImagesUiState()
}