package com.avolodin.dogsapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.avolodin.dogsapp.domain.GetBreedListUseCase
import com.avolodin.dogsapp.domain.model.BreedModel
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.observers.DisposableObserver
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Captor

class BreedListViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val getBreedListUseCase = mock<GetBreedListUseCase>()
    private val breedListViewModel = BreedListViewModel(getBreedListUseCase)

    @Captor
    private val captor = argumentCaptor<DisposableObserver<List<BreedModel>>>()

    @Test
    fun testLiveData() {
        assertNotNull(breedListViewModel.viewStateBreeds)
    }


}