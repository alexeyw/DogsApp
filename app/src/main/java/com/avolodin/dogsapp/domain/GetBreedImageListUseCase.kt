package com.avolodin.dogsapp.domain

import com.avolodin.dogsapp.domain.model.BreedImageModel
import io.reactivex.Observable
import javax.inject.Inject

class GetBreedImageListUseCase @Inject constructor(private val repository: DogsRepository) {
    fun getBreedImageList(breed: String): Observable<List<BreedImageModel>> =
        repository.getBreedImageList(breed)
}
