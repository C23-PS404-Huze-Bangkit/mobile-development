package com.bangkitc23ps404.huze.data.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

data class Cat(
    val id: String,
    val breed: String,
    val description: String,
    val food: String,
    val care: String,
    val image: String,
    val characteristics: Characteristics
)

data class Characteristics(
    val id: String,
    val life_span: String,
    val length: String,
    val weight: String,
    val origin: String,
    val affectionate: Int,
    val health: Int,
    val playfulness: Int,
    val kid_friendly: Int,
    val strangers_friendly: Int,
    val pet_friendly: Int,
    val groom: Int,
    val intelligence: Int
)

data class CatData(
    val status: String,
    val data: CatContainer
)

data class CatContainer(
    val cat: List<Cat>
)

class AmbilSpesifikDataKucing {
    fun fetchJsonDataById(id: String, callback: (Cat?) -> Unit) {
        val url = "https://huze-management.et.r.appspot.com/v1/cats/$id"

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(null)
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                val catData = Gson().fromJson(json, CatData::class.java)
                val catList = catData.data.cat
                if (catList.isNotEmpty()) {
                    val cat = catList[0]
                    callback(cat)
                } else {
                    callback(null)
                }
            }
        })
    }
}