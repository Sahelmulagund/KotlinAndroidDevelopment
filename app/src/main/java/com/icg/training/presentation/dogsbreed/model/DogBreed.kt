package com.icg.training.presentation.dogsbreed.model

import com.google.gson.annotations.SerializedName

data class DogBreed<T>(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: T
//    @SerializedName("message") val message: Map<String,List<String>>
)