package com.bangkitc23ps404.huze.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("data")
    val data: LoginResult,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class LoginResult(

    @field:SerializedName("accessToken")
    val accessToken: String,

    @field:SerializedName("refreshToken")
    val refreshToken: String

)