package com.icg.training.repository

import com.icg.training.model.Comments
import com.icg.training.model.Photos
import com.icg.training.model.Posts
import com.icg.training.retrofit.RetrofitClient
import retrofit2.Call

class MainRepository constructor(private val retrofitClient: RetrofitClient):IMainRepo {
    override fun getPhotos(): Call<List<Photos>> = retrofitClient.getPhotos()

    override fun getPosts(): Call<List<Posts>> = retrofitClient.getPosts()

    override fun getPostDetail(postId: Int): Call<Posts> = retrofitClient.getPostDetail(postId)

    override fun getComments(postId: Int): Call<List<Comments>> = retrofitClient.getComments(postId)
}