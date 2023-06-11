package com.bangkitc23ps404.huze.ui.modelml

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bangkitc23ps404.huze.R
import java.io.File


class HasilModelMl : AppCompatActivity() {
    private lateinit var predictedClassTextView: TextView
    private lateinit var confidenceTextView: TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_model_ml)

        predictedClassTextView = findViewById(R.id.predictedClassTextView)
        confidenceTextView = findViewById(R.id.confidenceTextView)
        imageView = findViewById(R.id.imageView)

        val predictedClass = intent.getStringExtra("predictedClass")
        val confidence = intent.getFloatExtra("confidence", 0f)
        val filePath = intent.getStringExtra("filePath")

        predictedClassTextView.text = predictedClass
        confidenceTextView.text = "Confidence: $confidence"

        if (filePath != null) {
            val imageFile = File(filePath)
            if (imageFile.exists()) {
                val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
                imageView.setImageBitmap(bitmap)
            }
        }
    }
}