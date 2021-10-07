package com.icg.training.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.icg.training.repository.IMainRepo
import com.icg.training.repository.MainRepository
import com.icg.training.retrofit.RetrofitClient

class ViewModelFactory:ViewModelProvider.Factory {
    init {
        getInstance()
    }
    companion object {
        @Volatile
        private var INSTANCE:IMainRepo?=null
    }
    fun getInstance() =
        INSTANCE ?: synchronized(ViewModelFactory::class.java){
            INSTANCE?:MainRepository(
                RetrofitClient.getInstance()
            ).also { INSTANCE = it }

            fun destroyInstance(){
                INSTANCE = null
            }
        }


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(IMainRepo::class.java).newInstance(INSTANCE)
    }

}








