package com.avolodin.dogsapp.domain

import com.avolodin.dogsapp.factory.ModelFactory
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Test

class GetBreedListUseCaseTest {

    private val dogsRepository = mock<DogsRepository>()
    private val getBreedListUseCase = GetBreedListUseCase(dogsRepository)

    @Test
    fun getBreedList() {
        val breedModelList = ModelFactory.makeBreedModelList(5)
        whenever(dogsRepository.getBreedList()).thenReturn(
            Observable.just(breedModelList)
        )
        val testObserver = getBreedListUseCase.getBreedList().test()
        testObserver.assertComplete()
        testObserver.assertValue(breedModelList)
    }
}