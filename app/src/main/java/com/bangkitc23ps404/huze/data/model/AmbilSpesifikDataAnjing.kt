package com.bangkitc23ps404.huze.data.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

data class Dog(
    val id: String,
    val breed: String,
    val description: String,
    val food: String,
    val care: String,
    val image: String,
    val characteristics: CharacteristicsDog
)

data class CharacteristicsDog(
    val id: String,
    val life_span: String,
    val height: String,
    val weight: String,
    val origin: String,
    val adaptability: Int,
    val friendliness: Int,
    val hngneeds: Int,
    val trainability: Int,
    val exercise: Int
)

data class DogData(
    val status: String,
    val data: DogContainer
)

data class DogContainer(
    val dog: List<Dog>
)

class AmbilSpesifikDataAnjing {
    fun fetchJsonDataById(id: String, callback: (Dog?) -> Unit) {
        val url = "https://huze-management.et.r.appspot.com/v1/dogs/$id"

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
                val dogData = Gson().fromJson(json, DogData::class.java)
                val dogList = dogData.data.dog
                if (dogList.isNotEmpty()) {
                    val dog = dogList[0]
                    callback(dog)
                } else {
                    callback(null)
                }
            }
        })
    }
}