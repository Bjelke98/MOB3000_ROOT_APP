package com.example.mob3000_root_app.data

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface RootService {

    @GET("article")
    suspend fun getArticles(): List<ArticleData>

    @GET("event")
    suspend fun getEvents(): List<EventData>

    companion object {
        var rootService: RootService? = null
        fun getInstance() : RootService {
            if (rootService == null){
                val moshi: Moshi = Moshi.Builder().build()
                rootService = Retrofit.Builder()
                    .baseUrl("https://linrik.herokuapp.com/api/")
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build().create(RootService::class.java)
            }
            return  rootService!!
        }
    }
}