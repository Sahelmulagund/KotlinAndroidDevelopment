package com.icg.training.presentation.dogsbreed.domain

import com.icg.training.data.remote.DogBreedService
import com.icg.training.presentation.dogsbreed.model.Breeds
import com.icg.training.presentation.dogsbreed.model.DogsBreedImage


class DogsRepo(private val dogsListApi:DogBreedService):IDogsRepo {
    override suspend fun getAllDogsBreed(): Breeds{
        val dogsList = dogsListApi.getAllDogsBreed()
        return dogsList!!
    }

    override suspend fun getDogsImage(breedName: String): DogsBreedImage {
       val dogsImage = dogsListApi.getBreedImages(breedName)
        return dogsImage!!
    }
}