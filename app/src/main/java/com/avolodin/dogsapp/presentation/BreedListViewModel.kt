package com.avolodin.dogsapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avolodin.dogsapp.domain.GetBreedListUseCase
import com.avolodin.dogsapp.presentation.viewstate.BreedsUiState
import com.avolodin.dogsapp.ui.model.BreedView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BreedListViewModel @Inject constructor(
    private val getBreedListUseCase: GetBreedListUseCase
) : ViewModel() {

    private val viewState = MutableLiveData<BreedsUiState>()
    val viewStateBreeds: LiveData<BreedsUiState> = viewState

    private val compositeDisposable = CompositeDisposable()

    fun loadBreedsList() {
        compositeDisposable.add(getBreedListUseCase.getBreedList()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { viewState.postValue(BreedsUiState.Loading) }
            .map { it -> it.map { BreedView(it.name) } }
            .subscribe(
                { viewState.postValue(BreedsUiState.Success(it)) },
                { viewState.postValue(BreedsUiState.Error(it.message.toString())) })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}