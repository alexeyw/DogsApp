package com.avolodin.dogsapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avolodin.dogsapp.domain.GetBreedImageListUseCase
import com.avolodin.dogsapp.presentation.viewstate.BreedImagesUiState
import com.avolodin.dogsapp.ui.model.ImageView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BreedImageListViewModel @Inject constructor(
    private val getBreedImageListUseCase: GetBreedImageListUseCase
) : ViewModel() {

    private val viewState = MutableLiveData<BreedImagesUiState>()
    val viewStateBreedImages: LiveData<BreedImagesUiState> = viewState

    private val compositeDisposable = CompositeDisposable()

    fun loadBreedImageList(breed: String) {
        compositeDisposable.add(getBreedImageListUseCase.getBreedImageList(breed)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { viewState.postValue(BreedImagesUiState.Loading) }
            .map { it -> it.map { ImageView(it.url) } }
            .subscribe(
                { viewState.postValue(BreedImagesUiState.Success(it)) },
                { viewState.postValue(BreedImagesUiState.Error(it.message.toString())) })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}