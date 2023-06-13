package com.bangkitc23ps404.huze.ui.modelml


import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bangkitc23ps404.huze.R
import com.bangkitc23ps404.huze.data.model.FileUploadResponse
import com.bangkitc23ps404.huze.data.network.ApiConfigML
import com.bangkitc23ps404.huze.databinding.FragmentModelmlBinding
import com.bangkitc23ps404.huze.utils.Utils.reduceFileImage
import com.bangkitc23ps404.huze.utils.Utils.uriToFile
import com.bangkitc23ps404.huze.utils.Utils.createTempFile
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class ModelmlFragment : Fragment() {
    private lateinit var binding: FragmentModelmlBinding
    private lateinit var currentPhotoPath: String
    private var getFile: File? = null

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 100
        private const val FILE_PROVIDER_AUTHORITY = "com.bangkitc23ps404.huze.fileprovider"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentModelmlBinding.inflate(inflater, container, false)
        return binding.root
    }
    private val requestCameraPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Permission granted, start the camera
                startTakePhoto()
            } else {
                // Permission denied, show a message to the user or handle the denial
                Toast.makeText(requireContext(), "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cameraButton.setOnClickListener {
            requestCameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
        binding.galleryButton.setOnClickListener { startGallery() }
        binding.uploadButton.setOnClickListener { uploadImage() }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private fun startTakePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(requireActivity().packageManager)
        createTempFile(requireActivity()).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                requireActivity(),
                "com.bangkitc23ps404.huze.fileprovider",
                it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val myFile = File(currentPhotoPath)
            myFile.let { file ->
                getFile = file
                binding.previewImageView.setImageBitmap(BitmapFactory.decodeFile(file.path))
            }
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val selectedImg = result.data?.data as Uri

            selectedImg.let { uri ->
                val myFile = uriToFile(uri, requireActivity())
                getFile = myFile
                binding.previewImageView.setImageURI(uri)
            }
        }
    }

    private fun uploadImage() {
        val filePath = getFile?.absolutePath
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)

            val requestImageFile = file.asRequestBody("image/jpeg".toMediaType())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "image",
                file.name,
                requestImageFile
            )

            val apiService = ApiConfigML.getApiService()
            val uploadImageRequest = apiService.uploadImage(imageMultipart)

            uploadImageRequest.enqueue(object : Callback<FileUploadResponse> {
                override fun onResponse(
                    call: Call<FileUploadResponse>,
                    response: Response<FileUploadResponse>
                ) {
                    if (response.isSuccessful) {
                        val fileUploadResponse = response.body()
                        val predictedClass = fileUploadResponse?.predicted_class
                        val confidence = fileUploadResponse?.confidence
                        Toast.makeText(
                            requireContext(),
                            "Predicted Class: $predictedClass\nConfidence: $confidence",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(requireContext(), HasilModelMl::class.java)
                        intent.putExtra("predictedClass", predictedClass)
                        intent.putExtra("confidence", confidence)
                        intent.putExtra("filePath", filePath)
                        startActivity(intent)

                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Failed to upload image.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<FileUploadResponse>, t: Throwable) {
                    Toast.makeText(
                        requireContext(),
                        "Failed to upload image.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        } else {
            Toast.makeText(
                requireContext(),
                "Silakan masukkan berkas gambar terlebih dahulu.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}