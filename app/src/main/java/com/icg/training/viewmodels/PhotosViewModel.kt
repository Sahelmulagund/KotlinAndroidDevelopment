package com.icg.training.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icg.training.model.Photos
import com.icg.training.repository.IMainRepo
import com.icg.training.result.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotosViewModel constructor(private val iMainRepo: IMainRepo):ViewModel() {
//    val photoList = MutableLiveData<List<Photos>>()
//    val errorMessage = MutableLiveData<String>()
    val obResult = MutableLiveData<Result<Response<List<Photos>>>>()



    fun getPhotos(){
        val response = iMainRepo.getPhotos()
        response.enqueue(object :  Callback<List<Photos>> {
            override fun onResponse(call: Call<List<Photos>>, response: Response<List<Photos>>) {
               obResult.value = Result.Success(response)
            }

            override fun onFailure(call: Call<List<Photos>>, t: Throwable) {
              obResult.value = Result.Failure(t.message)
            }

        })
    }

}