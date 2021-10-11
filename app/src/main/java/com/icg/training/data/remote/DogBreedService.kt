package com.icg.training.data.remote

import com.icg.training.presentation.dogsbreed.model.DogsBreedImage
import com.icg.training.presentation.dogsbreed.model.Breeds
import retrofit2.http.GET
import retrofit2.http.Path

interface DogBreedService {
    @GET("breeds/list/all")
    suspend fun getAllDogsBreed():Breeds?

    @GET("breed/{breed_name}/images/random")
    suspend fun getBreedImages(@Path("breed_name") breed_name:String): DogsBreedImage?

}