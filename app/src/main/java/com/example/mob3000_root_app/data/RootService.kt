package com.example.mob3000_root_app.data

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.internal.JavaNetCookieJar
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.net.CookieManager
import java.net.CookiePolicy


interface RootService {


    // Article API
    @GET("article")
    suspend fun getArticles(): List<ArticleData>

    @Headers("Content-Type: application/json")
    @POST("comment/{path}")
    suspend fun postComment(@Path("path") path: String, @Body commentData: CommentData) : ResponseStatus

    // Event API
    @GET("event")
    suspend fun getEvents(): List<EventData>

    // bruker API
    @POST("user/signup")
    suspend fun addUser(@Body userData: User): Call<User>// trenger mail fornavn etternavn og passord

    @GET("user")
    suspend fun getUser(): LoginStatus

    @Headers("Content-Type: application/json")
    @POST("user")
    suspend fun loginUser(@Body userLoginInfo: UserLoginInfo): LoginStatus// trenger data med email og passord

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

                val cookieManager = CookieManager()
                cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
                val cookieJar = JavaNetCookieJar(cookieManager)

                val client: OkHttpClient = OkHttpClient.Builder().cookieJar(cookieJar).build()

                val moshi: Moshi = Moshi.Builder().build()
                rootService = Retrofit.Builder()
                    .baseUrl("https://linrik.herokuapp.com/api/")
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .client(client)
                    .build().create(RootService::class.java)
            }
            return  rootService!!
        }
    }
}