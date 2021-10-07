package com.icg.training.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icg.training.model.Comments
import com.icg.training.model.Posts
import com.icg.training.repository.IMainRepo
import com.icg.training.result.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsViewModel constructor(private val iMainRepo: IMainRepo) :ViewModel(){
    val postsList =  MutableLiveData<Result<Response<List<Posts>>>>()
    val postDetail =  MutableLiveData<Result<Response<Posts>>>()
//    val errorMessage = MutableLiveData<String>()
    val commentList =  MutableLiveData<Result<Response<List<Comments>>>>()

    fun getPosts(){
        val response = iMainRepo.getPosts()
        response.enqueue(object : Callback<List<Posts>> {
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                postsList.value = Result.Success(response)
            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                postsList.value = Result.Failure(t.message)
            }

        })
    }
    fun getPostDetails(postId:Int){
        val postDetailResponse = iMainRepo.getPostDetail(postId)
        postDetailResponse.enqueue(object : Callback<Posts>{
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                postDetail.value = Result.Success(response)
            }

            override fun onFailure(call: Call<Posts>, t: Throwable) {
                postDetail.value = Result.Failure(t.message)
            }

        })
    }

    fun getCommentList(postId:Int){
        val getCommentsResponse = iMainRepo.getComments(postId)
        getCommentsResponse.enqueue(object : Callback<List<Comments>> {
            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                commentList.value = Result.Success(response)
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                commentList.value = Result.Failure(t.message)
            }

        })
    }



}
