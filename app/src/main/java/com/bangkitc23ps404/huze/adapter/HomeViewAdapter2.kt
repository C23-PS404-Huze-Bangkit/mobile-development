package com.bangkitc23ps404.huze.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkitc23ps404.huze.R
import com.bangkitc23ps404.huze.data.network.response.CatsItem
import com.bangkitc23ps404.huze.databinding.ItemCatsBinding
import com.bumptech.glide.Glide

class HomeViewAdapter2(private val listCats: List<CatsItem>) :
    RecyclerView.Adapter<HomeViewAdapter2.ViewHolder>() {

    private lateinit var onClickListener: OnClickListener

    class ViewHolder(var binding: ItemCatsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cats = listCats[position]
        val breedWithLineBreaks = cats.breed!!.replace(" ", "\n")

        holder.apply {
            binding.apply {
                tvRas.text = breedWithLineBreaks
                tvRas.textAlignment = View.TEXT_ALIGNMENT_CENTER
                Glide.with(itemView.context)
                    .load(cats.image)
                    .error(R.drawable.logoku2)
                    .into(ivCats)

                itemView.setOnClickListener {
                    onClickListener.onItemClick(cats)
                }
            }
        }
    }

    override fun getItemCount(): Int = listCats.size

    fun onItemClick(onClickListener: OnClickListener)
    {
        this.onClickListener = onClickListener
    }

    interface OnClickListener
    {
        fun onItemClick(item: CatsItem)
    }

}