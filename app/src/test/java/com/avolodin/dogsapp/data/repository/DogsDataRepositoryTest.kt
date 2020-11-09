package com.avolodin.dogsapp.data.repository

import com.avolodin.dogsapp.factory.ModelFactory
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Test

class DogsDataRepositoryTest {

    private val dogs = mock<Dogs>()
    private val dogsDataRepository = DogsDataRepository(dogs)

    @Test
    fun getBreedList() {
        val breedResponse = ModelFactory.makeBreedResponse(5)
        whenever(dogs.getBreedList()).thenReturn(
            Observable.just(breedResponse)
        )
        val testObserver = dogsDataRepository.getBreedList().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBreedImageList() {
        val breedImageResponse = ModelFactory.makeBreedImageResponse(5)
        whenever(dogs.getBreedImageList("test")).thenReturn(
            Observable.just(breedImageResponse)
        )
        val testObserver = dogsDataRepository.getBreedImageList("test").test()
        testObserver.assertComplete()
    }
}