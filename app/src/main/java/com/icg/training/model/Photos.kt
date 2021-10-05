package com.icg.training.model

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson


data class Photos(

    val albumId:Int,


    val id:Int,


    val title:String,


    val url:String,


    val thumbnailUrl:String
)

