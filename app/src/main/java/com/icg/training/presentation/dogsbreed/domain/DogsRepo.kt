package com.icg.training.presentation.dogsbreed.domain

import com.icg.training.data.model.ResultOf
import com.icg.training.data.remote.DogBreedService
import com.icg.training.presentation.dogsbreed.model.Breeds
import com.icg.training.presentation.dogsbreed.model.Dog
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class DogsRepo(private val dogsListApi:DogBreedService):IDogsRepo {
    override suspend fun getAllDogsBreedAsync(): ResultOf<List<Dog>> = withContext(Dispatchers.IO){
        val list = mutableListOf<Dog>()

        val dogBreedListDeferred = async { dogsListApi.getBreedsListAsync().execute() }
        val dogBreedListResponse = dogBreedListDeferred.await()

        val dogBreedOneName = dogBreedListResponse.body()?.message?.keys?.toList()?.random()
        val dogBreedTwoName = dogBreedListResponse.body()?.message?.keys?.toList()?.random()
        val dogBreedThreeName = dogBreedListResponse.body()?.message?.keys?.toList()?.random()
        val dogBreedFourName = dogBreedListResponse.body()?.message?.keys?.toList()?.random()
        val dogBreedFiveName = dogBreedListResponse.body()?.message?.keys?.toList()?.random()
        val dogBreedOneImageDeferred = async {
            dogBreedOneName?.let { dogsListApi.getImageByUrlAsync(it).execute() }

        }
        val dogBreedTwoImageDeferred = async {
            dogBreedTwoName?.let { dogsListApi.getImageByUrlAsync(it).execute() }

        }
        val dogBreedThreeImageDeferred = async {
            dogBreedThreeName?.let { dogsListApi.getImageByUrlAsync(it).execute() }

        }
        val dogBreedFourImageDeferred = async {
            dogBreedFourName?.let { dogsListApi.getImageByUrlAsync(it).execute() }

        }
        val dogBreedFiveImageDeferred = async {
            dogBreedFiveName?.let { dogsListApi.getImageByUrlAsync(it).execute() }

        }
        val dogBreedOne = dogBreedOneImageDeferred.await()
        val dogBreedTwo = dogBreedTwoImageDeferred.await()
        val dogBreedThree = dogBreedThreeImageDeferred.await()
        val dogBreedFour = dogBreedFourImageDeferred.await()
        val dogBreedFive = dogBreedFiveImageDeferred.await()

        if (dogBreedFive?.isSuccessful!!) list.add(Dog(dogBreedFiveName, dogBreedFive.body()?.message))
        if (dogBreedFour?.isSuccessful!!) list.add(Dog(dogBreedFourName, dogBreedFour.body()?.message))
        if (dogBreedThree?.isSuccessful!!) list.add(Dog(dogBreedThreeName, dogBreedThree.body()?.message))

        if (dogBreedTwo?.isSuccessful!!) list.add(Dog(dogBreedTwoName, dogBreedTwo.body()?.message))
        if (dogBreedOne?.isSuccessful!!) list.add(Dog(dogBreedOneName, dogBreedOne.body()?.message))

        ResultOf.Success(list)
    }

    override suspend fun getAllDogsAndImageList(): ResultOf<List<Dog>> {
        val list = mutableListOf<Dog>()

        val dogBreedList = dogsListApi.getBreedsList().message.keys.toList()
        dogBreedList.forEach {
            val dogImage = dogsListApi.getImageByUrl(it).message
            list.add(Dog(it,dogImage))
        }
        return ResultOf.Success(list)
    }


}