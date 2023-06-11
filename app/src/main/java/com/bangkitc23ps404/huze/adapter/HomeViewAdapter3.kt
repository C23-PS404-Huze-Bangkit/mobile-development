package com.bangkitc23ps404.huze.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkitc23ps404.huze.R
import com.bangkitc23ps404.huze.data.network.response.ArtikelItem
import com.bangkitc23ps404.huze.databinding.ItemArtikelBinding
import com.bumptech.glide.Glide

class HomeViewAdapter3(private val listArtikel: List<ArtikelItem>) :
    RecyclerView.Adapter<HomeViewAdapter3.ViewHolder>() {

    private lateinit var onClickListener: OnClickListener

    class ViewHolder(var binding: ItemArtikelBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemArtikelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Artikel = listArtikel[position]

        holder.apply {
            binding.apply {
                tvArtikel.text =Artikel.title
                Glide.with(itemView.context)
                    .load(Artikel.thumbnail)
                    .error(R.drawable.logoku2)
                    .into(ivArtikel)


                itemView.setOnClickListener {
                    onClickListener.onItemClick(Artikel)
                }
            }
        }
    }
    override fun getItemCount(): Int = listArtikel.size

    fun onItemClick(onClickListener: OnClickListener)
    {
        this.onClickListener = onClickListener
    }

    interface OnClickListener
    {
        fun onItemClick(item: ArtikelItem)
    }

}