package com.icg.training.repository

import com.icg.training.model.Comments
import com.icg.training.model.Photos
import com.icg.training.model.Posts
import retrofit2.Call

interface IMainRepo {
    fun getPhotos():Call<List<Photos>>
    fun getPosts():Call<List<Posts>>
    fun getPostDetail(postId:Int):Call<Posts>
    fun getComments(postId: Int):Call<List<Comments>>
}