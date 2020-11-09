package com.avolodin.dogsapp.domain

import com.avolodin.dogsapp.domain.model.BreedModel
import io.reactivex.Observable
import javax.inject.Inject

class GetBreedListUseCase @Inject constructor(private val repository: DogsRepository) {
    fun getBreedList(): Observable<List<BreedModel>> =
        repository.getBreedList()
}