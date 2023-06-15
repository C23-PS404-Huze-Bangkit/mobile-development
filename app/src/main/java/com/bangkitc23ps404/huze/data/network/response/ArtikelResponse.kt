package com.bangkitc23ps404.huze.data.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ArtikelResponse(

    @field:SerializedName("data")
    val data: DataArtikel? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class DataArtikel(

    @field:SerializedName("articles")
    val Artikel: List<ArtikelItem?>? = null
)
@Parcelize
data class ArtikelItem(

    @field:SerializedName("thumbnail")
    val thumbnail: String? = null,

    @field:SerializedName("article")
    val article: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("writer")
    val writer: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("tag")
    val tag: String? = null,
) : Parcelable