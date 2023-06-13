package com.bangkitc23ps404.huze.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkitc23ps404.huze.R
import com.bangkitc23ps404.huze.data.network.response.DogsItem
import com.bangkitc23ps404.huze.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    //    private val detailViewModel by viewModels<DetailViewModel>()
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_DOG = "extra_dog"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dogBreed: DogsItem? = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU)
            intent.getParcelableExtra(EXTRA_DOG)
        else
            intent.getParcelableExtra(EXTRA_DOG, DogsItem::class.java)

        setDataDog(dogBreed)

    }

    private fun setDataDog(dogsItem: DogsItem?) {
        binding.apply {
            Glide.with(applicationContext)
                .load(dogsItem?.image)
                .error(R.drawable.logoku2)
                .into(ivDogs)
            tvDogsBreed.text = dogsItem?.breed
            tvDogsDescription.text = dogsItem?.description
            tvDogsFood.text = dogsItem?.food
            tvDogsCare.text = dogsItem?.care
            tvDogsCharacteristicsLifeSpan.text = dogsItem?.characteristics?.lifeSpan
            tvDogsCharacteristicsHeight.text = dogsItem?.characteristics?.height
            tvDogsCharacteristicsWeight.text = dogsItem?.characteristics?.weight
            tvDogsCharacteristicsOrigin.text = dogsItem?.characteristics?.origin
            rbAdaptabilty.rating = dogsItem?.characteristics?.adaptability!!.toFloat()
            rbFriendliness.rating = dogsItem.characteristics.friendliness!!.toFloat()
            rbHngneeds.rating = dogsItem.characteristics.hngneeds!!.toFloat()
            rbTrainability.rating = dogsItem.characteristics.trainability!!.toFloat()
            rbExercise.rating = dogsItem.characteristics.exercise!!.toFloat()
        }
    }


}