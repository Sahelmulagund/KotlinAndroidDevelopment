package com.icg.training.presentation.dogsbreed.domain

import com.icg.training.presentation.dogsbreed.model.Breeds
import com.icg.training.presentation.dogsbreed.model.DogsBreedImage

interface IDogsRepo {

   suspend fun getAllDogsBreed():Breeds
    suspend fun getDogsImage(breedName:String): DogsBreedImage
}