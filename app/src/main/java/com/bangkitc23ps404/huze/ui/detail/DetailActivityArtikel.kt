package com.bangkitc23ps404.huze.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkitc23ps404.huze.R
import com.bangkitc23ps404.huze.data.network.response.ArtikelItem
import com.bangkitc23ps404.huze.databinding.ActivityDetailArtikelBinding
import com.bumptech.glide.Glide

class DetailActivityArtikel : AppCompatActivity() {
    private lateinit var binding: ActivityDetailArtikelBinding

    companion object {
        const val EXTRA_ARTIKEL = "extra_artikel"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ArtikelTitle: ArtikelItem? = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU)
            intent.getParcelableExtra(EXTRA_ARTIKEL)
        else
            intent.getParcelableExtra(EXTRA_ARTIKEL, ArtikelItem::class.java)

        setDataArtikel(ArtikelTitle)


    }

    private fun setDataArtikel(artikelItem: ArtikelItem?) {
        binding.apply {
            Glide.with(applicationContext)
                .load(artikelItem?.thumbnail)
                .error(R.drawable.logoku2)
                .into(ivArtikel)
            tvArtikelTitle.text = artikelItem?.title
            tvArtikelArticles.text = artikelItem?.article
            tvArtikelWriter.text = artikelItem?.writer
            tvArtikelTag.text = artikelItem?.tag
        }
    }

}