package com.icg.training.viewmodels

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.icg.training.model.Comments
import com.icg.training.model.Photos
import com.icg.training.model.Posts
import com.icg.training.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsViewModel constructor(private val bundle: Bundle?,private val mainRepository: MainRepository) :ViewModel(){
    val poststList = MutableLiveData<List<Posts>>()
    val postDetail = MutableLiveData<Posts>()
    val errorMessage = MutableLiveData<String>()
    val commentList = MutableLiveData<List<Comments>>()

    fun getPosts(){
        val response = mainRepository.getAllPosts()
        response.enqueue(object : Callback<List<Posts>> {
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                poststList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }
    fun getPostDetails(postId:Int){
        val postDetailResponse = mainRepository.getPostDetails(postId)
        postDetailResponse.enqueue(object : Callback<Posts>{
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                postDetail.postValue(response.body())
            }

            override fun onFailure(call: Call<Posts>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }

    fun getCommentList(postId:Int){
        val getCommentsResponse = mainRepository.getComments(postId)
        getCommentsResponse.enqueue(object : Callback<List<Comments>> {
            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                commentList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }


    class PostViewModelFactory constructor(private val bundle: Bundle?, private val mainRepository: MainRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(PostsViewModel::class.java)){
                PostsViewModel(bundle, this.mainRepository) as T
            }else{
                throw IllegalArgumentException("ViewModel not found")
            }
        }

    }
}