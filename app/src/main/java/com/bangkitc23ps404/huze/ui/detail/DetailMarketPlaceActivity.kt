package com.bangkitc23ps404.huze.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkitc23ps404.huze.R
import com.bangkitc23ps404.huze.data.network.response.ProductsItem
import com.bangkitc23ps404.huze.databinding.ActivityDetailMarketPlaceBinding
import com.bumptech.glide.Glide

class DetailMarketPlaceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMarketPlaceBinding

    companion object {
        const val EXTRA_PRODUCT = "extra_product"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMarketPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product: ProductsItem? = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU)
            intent.getParcelableExtra(EXTRA_PRODUCT)
        else
            intent.getParcelableExtra(EXTRA_PRODUCT, ProductsItem::class.java)

        setDataProduct(product)


    }

    private fun setDataProduct(productsItem: ProductsItem?) {
        binding.apply {
            Glide.with(applicationContext)
                .load(productsItem?.image)
                .error(R.drawable.logoku2)
                .into(ivProduct)
            tvProductName.text = productsItem?.name
            tvProductStore.text = productsItem?.store
            tvProductBrand.text = productsItem?.brand
            tvProductDescription.text = productsItem?.description
            tvProductStock.text = productsItem?.stock.toString()
            tvProductPrice.text = productsItem?.price.toString()
        }
    }

}