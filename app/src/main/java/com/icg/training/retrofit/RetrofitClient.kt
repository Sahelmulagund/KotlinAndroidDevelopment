package com.icg.training.retrofit

import com.icg.training.Constants
import com.icg.training.model.Comments
import com.icg.training.model.Photos
import com.icg.training.model.Posts
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitClient {
    @GET(Constants.ROUTES.PHOTO_URL)

    fun getPhotos():Call<List<Photos>>

    @GET(Constants.ROUTES.POSTS_URL)
    fun getPosts():Call<List<Posts>>

    @GET("${Constants.ROUTES.POSTS_URL}/{postId}")
    fun getPostDetail(@Path("postId") postId:Int):Call<Posts>

    @GET(Constants.ROUTES.COMMENT_URL)
    fun getComments(@Query("postId")postId: Int):Call<List<Comments>>

    companion object {
        var retrofitService:RetrofitClient?=null

        fun getInstance():RetrofitClient{
            if (retrofitService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.ROUTES.BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitClient::class.java)
            }
            return retrofitService!!
      }
    }
}