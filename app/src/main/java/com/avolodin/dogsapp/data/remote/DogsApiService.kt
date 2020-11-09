package com.avolodin.dogsapp.data.remote

import com.avolodin.dogsapp.data.remote.model.BreedImageResponse
import com.avolodin.dogsapp.data.remote.model.BreedResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsApiService {

    @GET("breeds/list")
    fun getAllBreeds(): Observable<BreedResponse>

    @GET("breed/{breed}/images")
    fun getBreedImages(@Path("breed") breed: String): Observable<BreedImageResponse>

}