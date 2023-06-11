package com.bangkitc23ps404.huze.data.network

import com.bangkitc23ps404.huze.data.model.LoginResponse
import com.bangkitc23ps404.huze.data.model.RegisterModel
import com.bangkitc23ps404.huze.data.network.response.ArtikelResponse
import com.bangkitc23ps404.huze.data.network.response.CatsResponse
import com.bangkitc23ps404.huze.data.network.response.DogsResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("fullname") fullname: String
    ): RegisterModel
    @GET("dogs")
    fun getDogs(): Call<DogsResponse>
    @GET("cats")
    fun getCats(): Call<CatsResponse>
    @GET("articles")
    fun getArtikel(): Call<ArtikelResponse>
}