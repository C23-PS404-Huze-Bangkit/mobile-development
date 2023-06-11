package com.bangkitc23ps404.huze.data.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class CatsResponse(

    @field:SerializedName("data")
    val data: DataCats? = null,

    @field:SerializedName("status")
    val status: String? = null
)

@Parcelize
data class CharacteristicsCats(

    @field:SerializedName("groom")
    val groom: Int? = null,

    @field:SerializedName("pet_friendly")
    val petFriendly: Int? = null,

    @field:SerializedName("origin")
    val origin: String? = null,

    @field:SerializedName("length")
    val length: String? = null,

    @field:SerializedName("weight")
    val weight: String? = null,

    @field:SerializedName("health")
    val health: Int? = null,

    @field:SerializedName("affectionate")
    val affectionate: Int? = null,

    @field:SerializedName("playfulness")
    val playfulness: Int? = null,

    @field:SerializedName("intelligence")
    val intelligence: Int? = null,

    @field:SerializedName("strangers_friendly")
    val strangersFriendly: Int? = null,

    @field:SerializedName("life_span")
    val lifeSpan: String? = null,

    @field:SerializedName("kid_friendly")
    val kidFriendly: Int? = null,

    @field:SerializedName("id")
    val id: String? = null
) : Parcelable

data class DataCats(

    @field:SerializedName("cats")
    val cats: List<CatsItem?>? = null
)
@Parcelize
data class CatsItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("characteristics")
    val characteristics: CharacteristicsCats? = null,

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
