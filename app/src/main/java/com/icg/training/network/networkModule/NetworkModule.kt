package com.icg.training.network.networkModule

import android.content.Context
import com.icg.training.BuildConfig
import com.icg.training.common.BaseApp
import com.icg.training.data.remote.DogBreedService
import com.icg.training.network.adapter.NullToEmptyStringAdapter
import com.icg.training.network.adapter.StringToBooleanAdapter
import com.icg.training.retrofit.RetrofitClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    fun makeApiService(): DogBreedService = Retrofit.Builder()
        .baseUrl("https://dog.ceo/api/")
        .client(okHttpClient(BaseApp.getAppContext()).build())
        .addConverterFactory(MoshiConverterFactory.create(moshiFactory()))
        .build()
        .create(DogBreedService::class.java)




    fun moshiFactory():Moshi{
        return Moshi.Builder()
            .add(StringToBooleanAdapter())
            .add(NullToEmptyStringAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    private fun okHttpClient(applicationContext:BaseApp) =
        okHttpBuilder(applicationContext)

    private fun okHttpBuilder(context: Context) = OkHttpClient.Builder()
        .addInterceptor(makeLoggingInterceptor())
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)


    private fun makeLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

}