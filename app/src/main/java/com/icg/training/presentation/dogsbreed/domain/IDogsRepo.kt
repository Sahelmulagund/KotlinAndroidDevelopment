package com.icg.training.presentation.dogsbreed.domain

import com.icg.training.data.model.ResultOf
import com.icg.training.presentation.dogsbreed.model.Breeds
import com.icg.training.presentation.dogsbreed.model.Dog
import com.icg.training.presentation.dogsbreed.model.DogsBreedImage
import kotlinx.coroutines.Deferred
import retrofit2.Call

interface IDogsRepo {

   suspend fun getAllDogsBreedAsync():ResultOf<List<Dog>>
//    suspend fun getDogsImage(breedName:String): DogsBreedImage
   suspend fun getAllDogsAndImageList():ResultOf<List<Dog>>
}