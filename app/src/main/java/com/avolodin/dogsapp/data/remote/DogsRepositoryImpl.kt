package com.avolodin.dogsapp.data.remote

import com.avolodin.dogsapp.data.remote.model.BreedImageResponse
import com.avolodin.dogsapp.data.remote.model.BreedResponse
import com.avolodin.dogsapp.data.repository.Dogs
import io.reactivex.Observable
import javax.inject.Inject

class DogsRepositoryImpl @Inject constructor(
    private val restApi: DogsApiService
) : Dogs {
    override fun getBreedList(): Observable<BreedResponse> = restApi.getAllBreeds()
    override fun getBreedImageList(breed: String): Observable<BreedImageResponse> =
        restApi.getBreedImages(breed)
}