package com.bangkitc23ps404.huze.ui.marketplace

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkitc23ps404.huze.data.network.ApiConfig
import com.bangkitc23ps404.huze.data.network.response.ProductResponse
import com.bangkitc23ps404.huze.data.network.response.ProductsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarketplaceViewModel : ViewModel() {

    private val _productList = MutableLiveData<List<ProductsItem>>()
    val productList: LiveData<List<ProductsItem>> = _productList

    init {
        getProduct()
    }

    private fun getProduct() {
        val call = ApiConfig.getApiService().getProduct()
        call.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                if (response.isSuccessful) {
                    val productResponse = response.body()
                    val productsItems = productResponse?.data?.products
                    _productList.value = productsItems as List<ProductsItem>

                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable)
            {
            }
        })
    }
}