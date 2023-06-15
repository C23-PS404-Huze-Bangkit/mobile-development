package com.bangkitc23ps404.huze.ui.modelml

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bangkitc23ps404.huze.R
import com.bangkitc23ps404.huze.data.model.AmbilSpesifikDataAnjing
import java.io.File
import java.text.DecimalFormat

class HasilModelMlAnjing : AppCompatActivity() {
    private lateinit var predictedClassTextView: TextView
    private lateinit var confidenceTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var adaptabilityRatingBar: RatingBar
    private lateinit var friendlinessRatingBar: RatingBar
    private lateinit var hngneedsRatingBar: RatingBar
    private lateinit var trainabilityRatingBar: RatingBar
    private lateinit var exerciseRatingBar: RatingBar
    private lateinit var descriptionTextView: TextView
    private lateinit var careTextView: TextView
    private lateinit var lifespanTextView: TextView
    private lateinit var heightTextView: TextView
    private lateinit var weightTextView: TextView
    private lateinit var originTextView: TextView
    private lateinit var foodTextView: TextView
    private lateinit var favoriteButton: Button
    private lateinit var homeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_model_ml_anjing)
        predictedClassTextView = findViewById(R.id.predictedClassTextView)
        confidenceTextView = findViewById(R.id.confidenceTextView)
        imageView = findViewById(R.id.imageView)
        lifespanTextView = findViewById(R.id.tv_dogs_characteristics_life_span)
        heightTextView = findViewById(R.id.tv_dogs_characteristics_height)
        weightTextView = findViewById(R.id.tv_dogs_characteristics_weight)
        originTextView = findViewById(R.id.tv_dogs_characteristics_origin)
        adaptabilityRatingBar = findViewById(R.id.rb_adaptability)
        friendlinessRatingBar = findViewById(R.id.rb_friendliness)
        hngneedsRatingBar = findViewById(R.id.rb_hngneeds)
        trainabilityRatingBar = findViewById(R.id.rb_trainability)
        exerciseRatingBar = findViewById(R.id.rb_exercise)
        descriptionTextView = findViewById(R.id.tv_dogs_description)
        careTextView = findViewById(R.id.tv_dogs_care)
        foodTextView = findViewById(R.id.tv_dogs_food)
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
        val dataFetcher = AmbilSpesifikDataAnjing()

        dataFetcher.fetchJsonDataById(id) { data ->
            runOnUiThread {
                if (data != null) {
                    lifespanTextView.text = data.characteristics.life_span
                    heightTextView.text = data.characteristics.height
                    weightTextView.text = data.characteristics.weight
                    originTextView.text = data.characteristics.origin
                    adaptabilityRatingBar.rating = data.characteristics.adaptability.toFloat()
                    friendlinessRatingBar.rating = data.characteristics.friendliness.toFloat()
                    hngneedsRatingBar.rating = data.characteristics.hngneeds.toFloat()
                    trainabilityRatingBar.rating = data.characteristics.trainability.toFloat()
                    exerciseRatingBar.rating = data.characteristics.exercise.toFloat()
                    descriptionTextView.text = data.description
                    careTextView.text = data.care
                    foodTextView.text = data.food
                } else {
                    println("Gagal mendapatkan data JSON.")
                }
            }
        }
    }
}