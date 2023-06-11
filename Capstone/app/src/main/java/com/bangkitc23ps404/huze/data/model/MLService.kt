package com.bangkitc23ps404.huze.data.model

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import retrofit2.Call

import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

data class FileUploadResponse(
    @field:SerializedName("confidence")
    val confidence: Float,

    @field:SerializedName("predicted_class")
    val predicted_class: String,

)

interface MLService {
    @Multipart
    @POST("/")
    fun uploadImage(
        @Part file: MultipartBody.Part
    ): Call<FileUploadResponse>
}