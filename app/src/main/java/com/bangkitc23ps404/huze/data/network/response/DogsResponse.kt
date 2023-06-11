package com.bangkitc23ps404.huze.data.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class DogsResponse(

    @field:SerializedName("data")
    val data: DataDogs? = null,

    @field:SerializedName("status")
    val status: String? = null,

    )

@Parcelize
data class CharacteristicsDogs(

    @field:SerializedName("life_span")
    val lifeSpan: String? = null,

    @field:SerializedName("hngneeds")
    val hngneeds: Int? = null,

    @field:SerializedName("origin")
    val origin: String? = null,

    @field:SerializedName("friendliness")
    val friendliness: Int? = null,

    @field:SerializedName("weight")
    val weight: String? = null,

    @field:SerializedName("exercise")
    val exercise: Int? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("adaptability")
    val adaptability: Int? = null,

    @field:SerializedName("trainability")
    val trainability: Int? = null,

    @field:SerializedName("height")
    val height: String? = null
) : Parcelable

@Parcelize
data class DogsItem(
    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("characteristics")
    val characteristics: CharacteristicsDogs? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("breed")
    val breed: String? = null,

    @field:SerializedName("food")
    val food: String? = null,

    @field:SerializedName("care")
    val care: String? = null
) : Parcelable

data class DataDogs(
    @field:SerializedName("dogs")
    val dogs: List<DogsItem>? = null
)
