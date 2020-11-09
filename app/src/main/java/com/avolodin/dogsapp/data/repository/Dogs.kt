package com.avolodin.dogsapp.data.repository

import com.avolodin.dogsapp.data.remote.model.BreedImageResponse
import com.avolodin.dogsapp.data.remote.model.BreedResponse
import io.reactivex.Observable

interface Dogs {
    fun getBreedList(): Observable<BreedResponse>
    fun getBreedImageList(breed: String): Observable<BreedImageResponse>
}