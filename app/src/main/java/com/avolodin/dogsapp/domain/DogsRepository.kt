package com.avolodin.dogsapp.domain

import com.avolodin.dogsapp.domain.model.BreedImageModel
import com.avolodin.dogsapp.domain.model.BreedModel
import io.reactivex.Observable

interface DogsRepository {
    fun getBreedList(): Observable<List<BreedModel>>
    fun getBreedImageList(breed: String): Observable<List<BreedImageModel>>
}