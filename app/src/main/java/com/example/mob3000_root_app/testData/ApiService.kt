package com.example.mob3000_root_app.testData

import com.example.mob3000_root_app.data.ArticleData
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


interface ApiService {
    @GET("article")
    suspend fun getArticles(): List<ArticleData>

    companion object {
        var rootService: ApiService? = null
        fun getInstance() : ApiService {
            if (rootService == null){
                val moshi: Moshi = Moshi.Builder().build()
                rootService = Retrofit.Builder()
                    .baseUrl("https://linrik.herokuapp.com/api/")
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build().create(ApiService::class.java)
            }
            return  rootService!!
        }
    }

}