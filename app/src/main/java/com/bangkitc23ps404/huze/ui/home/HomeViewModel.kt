package com.bangkitc23ps404.huze.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkitc23ps404.huze.data.network.ApiConfig
import com.bangkitc23ps404.huze.data.network.response.ArtikelItem
import com.bangkitc23ps404.huze.data.network.response.ArtikelResponse
import com.bangkitc23ps404.huze.data.network.response.CatsItem
import com.bangkitc23ps404.huze.data.network.response.CatsResponse
import com.bangkitc23ps404.huze.data.network.response.DogsItem
import com.bangkitc23ps404.huze.data.network.response.DogsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _listDog = MutableLiveData<List<DogsItem>>()
    private val _listCat = MutableLiveData<List<CatsItem>>()
    private val _listArtikel = MutableLiveData<List<ArtikelItem>>()
    val listDog: LiveData<List<DogsItem>> = _listDog
    val listCat: LiveData<List<CatsItem>> = _listCat
    val listArtikel: LiveData<List<ArtikelItem>> = _listArtikel

    init {
        getDogs()
        getCats()
        getArtikel()
    }

    private fun getDogs() {
        val call = ApiConfig.getApiService().getDogs()
        call.enqueue(object : Callback<DogsResponse> {
            override fun onResponse(call: Call<DogsResponse>, response: Response<DogsResponse>) {
                if (response.isSuccessful) {
                    val dogsResponse = response.body()
                    val dogsItems = dogsResponse?.data?.dogs
                    _listDog.value = dogsItems as List<DogsItem>

                }
            }

            override fun onFailure(call: Call<DogsResponse>, t: Throwable)
            {
            }
        })
    }
    private fun getCats() {
        val call = ApiConfig.getApiService().getCats()
        call.enqueue(object : Callback<CatsResponse> {
            override fun onResponse(call: Call<CatsResponse>, response: Response<CatsResponse>) {
                if (response.isSuccessful) {
                    val catsResponse = response.body()
                    val catsItems = catsResponse?.data?.cats
                    _listCat.value = catsItems as List<CatsItem>

                }
            }

            override fun onFailure(call: Call<CatsResponse>, t: Throwable)
            {
            }
        })
    }
    private fun getArtikel() {
        val call = ApiConfig.getApiService().getArtikel()
        call.enqueue(object : Callback<ArtikelResponse> {
            override fun onResponse(call: Call<ArtikelResponse>, response: Response<ArtikelResponse>) {
                if (response.isSuccessful) {
                    val ArtikelResponse = response.body()
                    val ArtikelItems = ArtikelResponse?.data?.Artikel
                    _listArtikel.value = ArtikelItems as List<ArtikelItem>

                }
            }

            override fun onFailure(call: Call<ArtikelResponse>, t: Throwable)
            {
            }
        })
    }
}