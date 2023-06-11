package com.bangkitc23ps404.huze.data.model

import com.google.gson.annotations.SerializedName

data class RegisterModel(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)