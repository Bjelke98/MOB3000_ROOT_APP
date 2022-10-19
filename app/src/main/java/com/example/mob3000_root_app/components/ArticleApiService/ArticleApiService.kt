package com.example.mob3000_root_app.components.ArticleApiService

import com.example.mob3000_root_app.data.ArticleData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://linrik.herokuapp.com/api/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
                        .baseUrl(BASE_URL).build()

interface ArticleApiService {
    @GET("article")
    suspend fun getAllArticles(): List<ArticleData>
//    @GET("article/article")
}

object ArticleAPI {
    val retrofitService: ArticleApiService by lazy{
        retrofit.create(ArticleApiService::class.java)
    }
}