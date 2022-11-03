package com.example.mob3000_root_app.data

import android.service.autofill.UserData
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface RootService {


    // Article API
    @GET("article")
    suspend fun getArticles(): List<ArticleData>

    suspend fun postComment(@Body commentData:Comment) : Call<Comment>

    // Event API
    @GET("event")
    suspend fun getEvents(): List<EventData>


    // bruker API
    @POST("user/signup")
    suspend fun addUser(@Body userData: User): Call<User>// trenger mail fornavn etternavn og passord

    @GET("user")
    suspend fun getUser(): User // trenger roller, fornavn etternavn og mail

    @POST("user")
    suspend fun loginUser(@Body userData: User): Call<User>// trenger data med email og passord

    @PUT("user")
    suspend fun updateUser(@Body userData: User): Call<User>// trenger samme data som registrer

    @DELETE("user")
    suspend fun deleteUser(@Body userData: User): Call<User> // trenger passord

    @DELETE("logout")
    suspend fun logout(): User

    @PUT("newpassword")
    suspend fun newPassword(@Body userData: User): Call<User> // trenger passord og nytt passord



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