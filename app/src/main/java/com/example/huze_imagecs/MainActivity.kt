package com.example.huze_imagecs

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.huze_imagecs.databinding.ActivityMainBinding
import com.example.huze_imagecs.ml.HuzeModel
import com.example.huze_imagecs.utils.Utils.createTempFile
import com.example.huze_imagecs.utils.Utils.uriToFile
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder

class MainActivity : AppCompatActivity() {
    private val imageSize = 299
    private lateinit var binding: ActivityMainBinding
    private lateinit var currentPhotoPath: String
    private var getFile: File? = null
    private lateinit var image: Bitmap
//    private var labels = application.assets.open("label.txt").bufferedReader().readLine()

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val myFile = File(currentPhotoPath)
            image = BitmapFactory.decodeFile(myFile.path)
            getFile = myFile
            binding.imageView.setImageBitmap(image)
            classifyImage(image)
        }
    }
    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, this@MainActivity)
            image = BitmapFactory.decodeFile(myFile.path)
            getFile = myFile
            binding.imageView.setImageURI(selectedImg)
            classifyImage(image)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            startTakePhoto()
        }
        binding.button2.setOnClickListener {
            startGallery()
        }
    }

    private fun startTakePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)
        createTempFile(application).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                this@MainActivity,
                "com.example.huze_imagecs",
                it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private fun classifyImage(image: Bitmap?) {
        try {
            val model = HuzeModel.newInstance(applicationContext)

            val inputFeature0 =
                TensorBuffer.createFixedSize(intArrayOf(1, 299, 299, 3), DataType.FLOAT32)
            inputFeature0.loadBuffer(convertBitmapToByteBuffer(image))

            // Run model inference and get the result.
            val outputs = model.process(inputFeature0)

            val outputFeature0: FloatArray = outputs.outputFeature0AsTensorBuffer.floatArray

            var maxIndex = 0
            outputFeature0.forEachIndexed{ index, fl ->
                if(outputFeature0[maxIndex] < fl){
                    maxIndex = index
                }
            }

//            binding.result.text = labels[maxIndex]

            // Release model resources if no longer used.
            model.close()
        } catch (e: IOException) {
            // TODO Handle the exception
        }
    }

    private fun convertBitmapToByteBuffer(bitmap: Bitmap?): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
        byteBuffer.order(ByteOrder.nativeOrder())
        bitmap?.let {
            val mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
            val intValues = IntArray(imageSize * imageSize)
            mutableBitmap.getPixels(intValues, 0, imageSize, 0, 0, imageSize, imageSize)
            var pixel = 0
            for (i in 0 until imageSize) {
                for (j in 0 until imageSize) {
                    val value = intValues[pixel++]
                    byteBuffer.putFloat(((value shr 16 and 0xFF)) / 255.0f)
                    byteBuffer.putFloat(((value shr 8 and 0xFF)) / 255.0f)
                    byteBuffer.putFloat(((value and 0xFF)) / 255.0f)
                }
            }
        }
        byteBuffer.rewind()
        return byteBuffer
    }
}