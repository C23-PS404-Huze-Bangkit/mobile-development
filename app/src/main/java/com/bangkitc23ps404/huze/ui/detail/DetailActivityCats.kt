package com.bangkitc23ps404.huze.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkitc23ps404.huze.R
import com.bangkitc23ps404.huze.data.network.response.CatsItem
import com.bangkitc23ps404.huze.databinding.ActivityDetailCatsBinding
import com.bumptech.glide.Glide

class DetailActivityCats : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCatsBinding

    companion object {
        const val EXTRA_CAT = "extra_cat"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val catBreed: CatsItem? = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU)
            intent.getParcelableExtra(EXTRA_CAT)
        else
            intent.getParcelableExtra(EXTRA_CAT, CatsItem::class.java)

        setDataCat(catBreed)


    }

    private fun setDataCat(catsItem: CatsItem?) {
        binding.apply {
            Glide.with(applicationContext)
                .load(catsItem?.image)
                .error(R.drawable.logoku2)
                .into(ivCats)
            tvCatsBreed.text = catsItem?.breed
            tvCatsDescription.text = catsItem?.description
            tvCatsFood.text = catsItem?.food
            tvCatsCare.text = catsItem?.care
            tvCatsCharacteristicsLifeSpan.text = catsItem?.characteristics?.lifeSpan
            tvCatsCharacteristicsLength.text = catsItem?.characteristics?.length
            tvCatsCharacteristicsWeight.text = catsItem?.characteristics?.weight
            tvCatsCharacteristicsOrigin.text = catsItem?.characteristics?.origin
            rbAffectionate.rating = catsItem?.characteristics?.affectionate!!.toFloat()
            rbHealth.rating = catsItem.characteristics.health!!.toFloat()
            rbPlayfulness.rating = catsItem.characteristics.playfulness!!.toFloat()
            rbKidFriendly.rating = catsItem.characteristics.kidFriendly!!.toFloat()
            rbStrangersFriendly.rating = catsItem.characteristics.strangersFriendly!!.toFloat()
            rbPetFriendly.rating = catsItem.characteristics.petFriendly!!.toFloat()
            rbGroom.rating = catsItem.characteristics.groom!!.toFloat()
            rbIntelligence.rating = catsItem.characteristics.intelligence!!.toFloat()
        }
    }

}