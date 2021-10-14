package com.icg.training.data.remote

import androidx.annotation.UiThread
import com.icg.training.presentation.dogsbreed.model.DogsBreedImage
import com.icg.training.presentation.dogsbreed.model.Breeds
import com.icg.training.presentation.dogsbreed.model.DogBreed
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogBreedService {

    @GET("breeds/list/all")
    fun getBreedsListAsync(): Call<DogBreed<Map<String, List<String>>>>

    @GET("breed/{breedName}/images/random")
    fun getImageByUrlAsync(@Path("breedName") breedName: String): Call<DogBreed<String>>


    @GET("breeds/list/all")
   suspend fun getBreedsList(): DogBreed<Map<String, List<String>>>

    @GET("breed/{breedName}/images/random")
   suspend fun getImageByUrl(@Path("breedName") breedName: String): DogBreed<String>

}