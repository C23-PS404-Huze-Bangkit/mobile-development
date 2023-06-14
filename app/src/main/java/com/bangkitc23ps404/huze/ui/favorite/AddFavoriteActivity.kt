package com.bangkitc23ps404.huze.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.bangkitc23ps404.huze.R
import com.bangkitc23ps404.huze.data.model.FavoritePet

class AddFavoriteActivity : AppCompatActivity() {

    private lateinit var idEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var speciesEditText: EditText
    private lateinit var breedEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var createdAtEditText: EditText
    private lateinit var updatedAtEditText: EditText
    private lateinit var ownerEditText: EditText
    private lateinit var imageUrlEditText: EditText
    private lateinit var imageNameEditText: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_favorite)

        idEditText = findViewById(R.id.idEditText)
        nameEditText = findViewById(R.id.nameEditText)
        speciesEditText = findViewById(R.id.speciesEditText)
        breedEditText = findViewById(R.id.breedEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        createdAtEditText = findViewById(R.id.createdAtEditText)
        updatedAtEditText = findViewById(R.id.updatedAtEditText)
        ownerEditText = findViewById(R.id.ownerEditText)
        imageUrlEditText = findViewById(R.id.imageUrlEditText)
        imageNameEditText = findViewById(R.id.imageNameEditText)
        submitButton = findViewById(R.id.submitButton)

        val filePath = intent.getStringExtra("filePath")
        val predictedClass = intent.getStringExtra("predictedClass")
        idEditText.setText(predictedClass)
        imageUrlEditText.setText(filePath)

        submitButton.setOnClickListener {
            val id = idEditText.text.toString()
            val name = nameEditText.text.toString()
            val species = speciesEditText.text.toString()
            val breed = breedEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val createdAt = createdAtEditText.text.toString()
            val updatedAt = updatedAtEditText.text.toString()
            val owner = ownerEditText.text.toString()
            val imageUrl = imageUrlEditText.text.toString()
            val imageName = imageNameEditText.text.toString()

            // Create FavoritePet object with the entered data
            val favoritePet = FavoritePet(
                id,
                name,
                species,
                breed,
                description,
                createdAt,
                updatedAt,
                owner,
                imageUrl,
                imageName
            )

            // Perform the desired action with the favoritePet object, such as saving it to a database

            // Finish the activity
            finish()
        }
    }
}