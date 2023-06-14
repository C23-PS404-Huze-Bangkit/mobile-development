package com.bangkitc23ps404.huze.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkitc23ps404.huze.R
import com.bangkitc23ps404.huze.data.network.response.DogsItem
import com.bangkitc23ps404.huze.databinding.ItemDogsBinding
import com.bumptech.glide.Glide

class HomeViewAdapter1(private val listDogs: List<DogsItem>) :
    RecyclerView.Adapter<HomeViewAdapter1.ViewHolder>() {

    private lateinit var onClickListener: OnClickListener

    class ViewHolder(var binding: ItemDogsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDogsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dogs = listDogs[position]
        val breedWithLineBreaks = dogs.breed!!.replace(" ", "\n")
        holder.apply {
            binding.apply {
                tvRas.text = breedWithLineBreaks
                tvRas.textAlignment = View.TEXT_ALIGNMENT_CENTER
                Glide.with(itemView.context)
                    .load(dogs.image)
                    .error(R.drawable.logoku2)
                    .into(ivDogs)

                itemView.setOnClickListener {
                    onClickListener.onItemClick(dogs)
                }
            }
        }
    }

    override fun getItemCount(): Int = listDogs.size

    fun onItemClick(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onItemClick(item: DogsItem)
    }
}