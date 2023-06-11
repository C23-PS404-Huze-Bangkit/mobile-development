package com.bangkitc23ps404.huze.ui.modelml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bangkitc23ps404.huze.R


class HasilModelMl : AppCompatActivity() {
    private lateinit var predictedClassTextView: TextView
    private lateinit var confidenceTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_model_ml)

        predictedClassTextView = findViewById(R.id.predictedClassTextView)
        confidenceTextView = findViewById(R.id.confidenceTextView)

        val predictedClass = intent.getStringExtra("predictedClass")
        val confidence = intent.getFloatExtra("confidence", 0f)
        predictedClassTextView.text = predictedClass
        confidenceTextView.text = "Confidence: $confidence"
    }
}