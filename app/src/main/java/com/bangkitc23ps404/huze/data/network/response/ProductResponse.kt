package com.bangkitc23ps404.huze.data.network.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ProductResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class ProductsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("store")
	val store: String? = null,

	@field:SerializedName("stock")
	val stock: Int? = null,

	@field:SerializedName("brand")
	val brand: String? = null
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("products")
	val products: List<ProductsItem?>? = null
) : Parcelable
