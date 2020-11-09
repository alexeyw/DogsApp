package com.avolodin.dogsapp.data.remote

import com.avolodin.dogsapp.factory.ModelFactory.makeBreedImageResponse
import com.avolodin.dogsapp.factory.ModelFactory.makeBreedResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Test

class DogsRepositoryImplTest {

    private val restApi = mock<DogsApiService>()
    private val dogsRepositoryImpl = DogsRepositoryImpl(restApi)

    @Test
    fun getBreedList() {
        val breedResponse = makeBreedResponse(5)
        whenever(restApi.getAllBreeds()).thenReturn(
            Observable.just(breedResponse)
        )
        val testObserver = dogsRepositoryImpl.getBreedList().test()
        testObserver.assertComplete()
        testObserver.assertValue(breedResponse)
    }

    @Test
    fun getBreedImageList() {
        val breedImageResponse = makeBreedImageResponse(5)
        whenever(restApi.getBreedImages("test")).thenReturn(
            Observable.just(breedImageResponse)
        )
        val testObserver = dogsRepositoryImpl.getBreedImageList("test").test()
        testObserver.assertComplete()
        testObserver.assertValue(breedImageResponse)
    }
}