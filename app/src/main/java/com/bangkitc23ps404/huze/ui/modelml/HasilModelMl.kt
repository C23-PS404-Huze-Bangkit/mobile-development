package com.bangkitc23ps404.huze.ui.modelml

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bangkitc23ps404.huze.R
import com.bangkitc23ps404.huze.data.model.AmbilSpesifikDataKucing
import com.bangkitc23ps404.huze.ui.favorite.AddFavoriteActivity
import com.bangkitc23ps404.huze.ui.favorite.FavoriteFragment
import java.io.File
import java.text.DecimalFormat

class HasilModelMl : AppCompatActivity() {
    private lateinit var predictedClassTextView: TextView
    private lateinit var confidenceTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var affectionateRatingBar: RatingBar
    private lateinit var healthRatingBar: RatingBar
    private lateinit var playfulnessRatingBar: RatingBar
    private lateinit var kidFriendlyRatingBar: RatingBar
    private lateinit var strangersFriendlyRatingBar: RatingBar
    private lateinit var petFriendlyRatingBar: RatingBar
    private lateinit var groomRatingBar: RatingBar
    private lateinit var intelligenceRatingBar: RatingBar
    private lateinit var descriptionTextView: TextView
    private lateinit var careTextView: TextView
    private lateinit var lifespanTextView: TextView
    private lateinit var lengthTextView: TextView
    private lateinit var weightTextView: TextView
    private lateinit var originTextView: TextView
    private lateinit var foodTextView: TextView
    private lateinit var favoriteButton: Button
    private lateinit var homeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_model_ml)
        predictedClassTextView = findViewById(R.id.predictedClassTextView)
        confidenceTextView = findViewById(R.id.confidenceTextView)
        imageView = findViewById(R.id.imageView)
        lifespanTextView = findViewById(R.id.tv_cats_characteristics_life_span)
        lengthTextView = findViewById(R.id.tv_cats_characteristics_length)
        weightTextView = findViewById(R.id.tv_cats_characteristics_weight)
        originTextView = findViewById(R.id.tv_cats_characteristics_origin)
        affectionateRatingBar = findViewById(R.id.rb_affectionate)
        healthRatingBar = findViewById(R.id.rb_health)
        playfulnessRatingBar = findViewById(R.id.rb_playfulness)
        kidFriendlyRatingBar = findViewById(R.id.rb_kid_friendly)
        strangersFriendlyRatingBar = findViewById(R.id.rb_strangers_friendly)
        petFriendlyRatingBar = findViewById(R.id.rb_pet_friendly)
        groomRatingBar = findViewById(R.id.rb_groom)
        intelligenceRatingBar = findViewById(R.id.rb_intelligence)
        descriptionTextView = findViewById(R.id.tv_cats_description)
        careTextView = findViewById(R.id.tv_cats_care)
        foodTextView = findViewById(R.id.tv_cats_food)
        favoriteButton = findViewById(R.id.button_tofavorite)
        homeButton = findViewById(R.id.button_tohome)

        val predictedClass = intent.getStringExtra("predictedClass")
        val confidence = intent.getFloatExtra("confidence", 2f)
        val decimalFormat = DecimalFormat("#.##")
        val formattedConfidence = decimalFormat.format(confidence)
        val filePath = intent.getStringExtra("filePath")
        predictedClassTextView.text = predictedClass
        confidenceTextView.text = "Confidence: $formattedConfidence %"

        if (filePath != null) {
            val imageFile = File(filePath)
            if (imageFile.exists()) {
                val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
                imageView.setImageBitmap(bitmap)
            }
        }
        val id = predictedClass?.replace(" ", "_")?.toLowerCase() ?: ""
        val dataFetcher = AmbilSpesifikDataKucing()

        dataFetcher.fetchJsonDataById(id) { data ->
            runOnUiThread {
                if (data != null) {
                    lifespanTextView.text = data.characteristics.life_span
                    lengthTextView.text = data.characteristics.length
                    weightTextView.text = data.characteristics.weight
                    originTextView.text = data.characteristics.origin
                    affectionateRatingBar.rating = data.characteristics.affectionate.toFloat()
                    healthRatingBar.rating = data.characteristics.health.toFloat()
                    playfulnessRatingBar.rating = data.characteristics.playfulness.toFloat()
                    kidFriendlyRatingBar.rating = data.characteristics.kid_friendly.toFloat()
                    strangersFriendlyRatingBar.rating = data.characteristics.strangers_friendly.toFloat()
                    petFriendlyRatingBar.rating = data.characteristics.pet_friendly.toFloat()
                    groomRatingBar.rating = data.characteristics.groom.toFloat()
                    intelligenceRatingBar.rating = data.characteristics.intelligence.toFloat()
                    descriptionTextView.text = data.description
                    careTextView.text = data.care
                    foodTextView.text = data.food
                } else {
                    println("Gagal mendapatkan data JSON.")
                }
            }
        }
        favoriteButton.setOnClickListener {
            val intent = Intent(this, AddFavoriteActivity::class.java)
            intent.putExtra("filePath", intent.getStringExtra("filePath"))
            intent.putExtra("predictedClass", predictedClassTextView.text.toString())
            startActivity(intent)
        }
    }
}