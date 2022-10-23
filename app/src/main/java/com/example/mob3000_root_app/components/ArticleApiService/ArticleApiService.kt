package com.example.mob3000_root_app.components.ArticleApiService

import com.example.mob3000_root_app.data.ArticleData
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


interface ArticleApiService {
    @GET("article")
    suspend fun getArticles(): List<ArticleData>

    companion object {
        var rootService: ArticleApiService? = null
        fun getInstance() : ArticleApiService {
            if (rootService == null){
                val moshi: Moshi = Moshi.Builder().build()
                rootService = Retrofit.Builder()
                    .baseUrl("https://linrik.herokuapp.com/api/")
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build().create(ArticleApiService::class.java)
            }
            return  rootService!!
        }
    }

}