package com.bangkitc23ps404.huze.ui.marketplace

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkitc23ps404.huze.R
import com.bangkitc23ps404.huze.data.network.response.ProductsItem
import com.bangkitc23ps404.huze.databinding.ItemProductBinding
import com.bumptech.glide.Glide

class ProductAdapter(private val productList: List<ProductsItem>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private lateinit var onClickListener: OnClickListener

    class ViewHolder(var binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]

        holder.apply {
            binding.apply {

                productName.text = product.name
                productPrice.text = "Rp ${product.price.toString()}"
                productSeller.text = product.brand

                Glide.with(itemView.context)
                    .load(product.image)
                    .error(R.drawable.logoku)
                    .into(productImage)

                itemView.setOnClickListener {
                    onClickListener.onItemClick(product)

                }
            }
        }
    }

    override fun getItemCount(): Int = productList.size

    fun onItemClick(onClickListener: OnClickListener)
    {
        this.onClickListener = onClickListener
    }

    interface OnClickListener
    {
        fun onItemClick(item: ProductsItem)
    }
}