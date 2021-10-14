package com.icg.training.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.icg.training.data.model.ResultOf
import com.icg.training.presentation.dogsbreed.domain.IDogsRepo
import com.icg.training.presentation.dogsbreed.model.Dog
import com.icg.training.presentation.dogsbreed.model.DogsBreedImage
import com.icg.training.result.Result
import com.icg.training.util.launchPeriodically
import kotlinx.coroutines.*
import org.json.JSONObject
import kotlin.coroutines.*

class dogsViewModel(val dogsRepo:IDogsRepo):ViewModel(),CoroutineScope {
    private val parentJob = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + parentJob

    val obDogsList = MutableLiveData<ResultOf<List<Dog>>?>()
//    val obDogsImage = MutableLiveData<ResultOf<DogsBreedImage>>()

    fun performDelayAction(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                delay(3000)
           
            }
        }
    }


    fun getDogListAutoReload(){

        viewModelScope.launchPeriodically(6000){
            launch {
                val list = runCatching { dogsRepo.getAllDogsBreedAsync() }
                list.onSuccess {
                    obDogsList.value = it
                }
            }
        }

    }
    fun getDogList(){
        viewModelScope.launch {
            runCatching {
                dogsRepo.getAllDogsBreedAsync()
            }.onSuccess {
                obDogsList.value = it
            }
        }
    }

    fun getDogListSynchronously(){
        viewModelScope.launch {
            runCatching {
                dogsRepo.getAllDogsAndImageList()
            }.onSuccess {
                obDogsList.value = it
            }
        }
    }

}