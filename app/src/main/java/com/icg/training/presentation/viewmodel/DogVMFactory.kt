package com.icg.training.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.icg.training.network.networkModule.NetworkModule
import com.icg.training.presentation.dogsbreed.domain.DogsRepo
import com.icg.training.presentation.dogsbreed.domain.IDogsRepo

class DogVMFactory:ViewModelProvider.Factory {
    init {
        getInstance()
    }
    companion object{
        @Volatile
        private var INSTANCE:IDogsRepo?=null
        fun getInstance() =
            INSTANCE ?: synchronized(DogVMFactory::class.java){
                INSTANCE?:DogsRepo(
                    NetworkModule.makeApiService()
                ).also { INSTANCE = it }
            }
        fun destroyInstance(){
            INSTANCE = null
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IDogsRepo::class.java).newInstance(INSTANCE)
    }
}