package com.avolodin.dogsapp.data.repository

import com.avolodin.dogsapp.domain.DogsRepository
import com.avolodin.dogsapp.domain.model.BreedImageModel
import com.avolodin.dogsapp.domain.model.BreedModel
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogsDataRepository @Inject constructor(
    private val dogs: Dogs
) : DogsRepository {

    override fun getBreedList(): Observable<List<BreedModel>> =
        dogs.getBreedList().map {
            it.message?.map { breed ->
                BreedModel(breed.orEmpty())
            }.orEmpty()
        }

    override fun getBreedImageList(breed: String): Observable<List<BreedImageModel>> =
        dogs.getBreedImageList(breed).map {
            it.message?.map { breedImage ->
                BreedImageModel(breedImage.orEmpty())
            }.orEmpty()
        }
}