package com.example.mob3000_root_app.data

import com.example.mob3000_root_app.data.apiResponse.*
import com.example.mob3000_root_app.screens.admin.apiRequest.*
import com.squareup.moshi.Moshi
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.internal.JavaNetCookieJar
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.net.CookieManager
import java.net.CookiePolicy


interface RootService {

    @Multipart
    @POST("article")
    suspend fun postArticle(
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part body: MultipartBody.Part,
    ) : ResponseStatus

    // Article API
    @GET("article")
    suspend fun getArticles(): List<ArticleData>

    @GET("article/{id}")
    suspend fun getArticleByID(@Path("id") articleid: String): ArticleData

    // Event API
    @GET("event")
    suspend fun getEvents(): List<EventData>

    @Headers("Content-Type: application/json")
    @HTTP(method = "DELETE", path = "event", hasBody = true)
    suspend fun deleteEventById(@Body eventid: EventId): ResponseStatus

    @GET("event/id/{path}")
    suspend fun getEventByID(@Path("path") eventid: String): EventData



    // For både article og event basert på @path
    @Headers("Content-Type: application/json")
    @POST("comment/{path}")
    suspend fun postComment(@Path("path") path: String, @Body commentData: CommentData) : ResponseStatus

    // bruker API
    @POST("user/signup")
    suspend fun addUser(@Body userData: User): Call<User>// trenger mail fornavn etternavn og passord

    @GET("user")
    suspend fun getUser(): LoginStatus

    @Headers("Content-Type: application/json")
    @POST("user")
    suspend fun loginUser(@Body userLoginInfo: UserLoginInfo): LoginStatus// trenger data med email og passord

    @PUT("user/all")
    suspend fun updateUser(@Body nameChange: NameChange): ResponseStatus// trenger samme data som registrer

    @DELETE("user")
    suspend fun deleteUser(@Body deleteUser: DeleteUser): ResponseStatus // trenger passord

    @GET("user/logout")
    suspend fun logout(): ResponseStatus

    @PUT("user/newpassword")
    suspend fun newPassword(@Body passwordChange: PasswordChange): ResponseStatus // trenger passord og nytt passord



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