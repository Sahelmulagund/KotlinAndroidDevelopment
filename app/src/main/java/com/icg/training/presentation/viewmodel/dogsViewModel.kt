package com.icg.training.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.icg.training.data.model.ResultOf
import com.icg.training.presentation.dogsbreed.domain.IDogsRepo
import com.icg.training.presentation.dogsbreed.model.Breeds
import com.icg.training.presentation.dogsbreed.model.DogsBreedImage
import com.squareup.moshi.Json
import kotlinx.coroutines.*
import org.json.JSONObject
import kotlin.coroutines.*

class dogsViewModel(val dogsRepo:IDogsRepo):ViewModel(),CoroutineScope {
    private val parentJob = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + parentJob

    val obDogsList = MutableLiveData<ResultOf<List<String>>?>()
    val obDogsImage = MutableLiveData<ResultOf<DogsBreedImage>>()

    fun performDelayAction(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                delay(3000)

            }
        }
    }

    fun getDogImage(breedName:String){
        launch {
            runCatching {
                dogsRepo.getDogsImage(breedName)
            }.onSuccess {
                if (it == null){
                    obDogsImage.value = ResultOf.Empty("No Dog Breed Found")
                }else{

                    obDogsImage.value = ResultOf.Success(it)

                }
            }.onFailure {
                obDogsImage.value = ResultOf.Failure(it.localizedMessage!!)
            }
        }
    }
    fun getDogsList(){
        launch {
            runCatching {
                dogsRepo.getAllDogsBreed()
            }.onSuccess {
                if (it == null)
                    obDogsList.value = ResultOf.Empty("No Dog Breed Found")
                else {
                    val tempArr = ArrayList<String>()
                    val toJson = Gson().toJson(it)

                    val obj = JSONObject(toJson)
                    val listJsonPath = obj.getJSONObject("message")
                    val itr = listJsonPath.keys()
                    while (itr.hasNext()){
                        tempArr.add(itr.next())
                    }

                    obDogsList.value = ResultOf.Success(tempArr)

                }
            }.onFailure {
                obDogsList.value = ResultOf.Failure(it.localizedMessage)
            }
        }
    }

}