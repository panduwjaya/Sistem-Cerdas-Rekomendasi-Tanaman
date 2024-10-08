package com.example.sistemcerdasrekomendasitanaman.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.sistemcerdasrekomendasitanaman.R
import com.example.sistemcerdasrekomendasitanaman.data.database.DummyData
import com.example.sistemcerdasrekomendasitanaman.data.network.ApiConfig
import com.example.sistemcerdasrekomendasitanaman.data.network.ApiService
import com.example.sistemcerdasrekomendasitanaman.data.response.PredictionResponse
import com.example.sistemcerdasrekomendasitanaman.databinding.HolderImageBinding
import com.example.sistemcerdasrekomendasitanaman.ui.primary.detail_tanaman.DetailTanamanActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class HolderImageActivity : AppCompatActivity() {

    private lateinit var binding: HolderImageBinding
    private lateinit var apiService: ApiService
    private lateinit var progressDialog: AlertDialog

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            this,
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HolderImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        val imageUri = intent.getStringExtra("image_uri")?.let { Uri.parse(it) }

        if (imageUri != null) {
            binding.previewImageView.setImageURI(imageUri)
        } else {
            Log.d("HolderImageActivity", "No image URI received")
        }

        apiService = ApiConfig().getApiService(this)

        binding.usePhotoBtn.setOnClickListener {
            uploadImage()
        }

        binding.cancelImgBtn.setOnClickListener {
            finish()
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun uploadImage() {
        val imageUri = intent.getStringExtra("image_uri")?.let { Uri.parse(it) }
        if (imageUri != null) {
            try {
                val inputStream = contentResolver.openInputStream(imageUri)
                val file = File.createTempFile("temp", ".jpg", cacheDir)
                file.outputStream().use { outputStream ->
                    inputStream?.copyTo(outputStream)
                }
                val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
                val multipartBody = MultipartBody.Part.createFormData("file", file.name, requestBody)

                val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_analisis, null)
                progressDialog = AlertDialog.Builder(this)
                    .setView(dialogView)
                    .setCancelable(false)
                    .create()
                progressDialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                progressDialog.window?.setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg))
                progressDialog.show()

                apiService.predict(multipartBody).enqueue(object : Callback<PredictionResponse> {
                    override fun onResponse(call: Call<PredictionResponse>, response: Response<PredictionResponse>) {
                        if (response.isSuccessful) {
                            val prediction = response.body()?.plant
                            Log.d("Prediction", prediction.toString())

                            if (prediction != null) {
                                val plantName = prediction.trim()
                                Log.d("HolderImageActivity", "Tanaman berhasil diprediksi: $plantName")

                                val plantDetail = DummyData.getPlantByName(plantName)

                                if (plantDetail != null) {
                                    val intent = Intent(this@HolderImageActivity, DetailTanamanActivity::class.java)
                                    intent.putExtra("plantDetail", plantDetail)
                                    Log.d("HolderImageActivity", "Plant detail is: $plantDetail")
                                    startActivity(intent)
                                    Log.d("HolderImageActivity", "berhasil masuk ke detail tanaman")
                                } else {
                                    Toast.makeText(this@HolderImageActivity, "Tanaman tidak ditemukan", Toast.LENGTH_LONG).show()
                                    Log.d("HolderImageActivity", "tidak berhasil masuk ke detail tanaman")
                                }
                            }
                        } else {
                            Log.e("Prediction", "Gagal memprediksi gambar")
                        }

                        progressDialog.dismiss()
                    }

                    override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                        Log.e("Prediction", "Gagal mengirimkan gambar")
                        progressDialog.dismiss()
                    }
                })
            } catch (e: Exception) {
                Log.e("Upload", "Error saat memproses gambar: ${e.message}")
            }
        } else {
            Log.e("Upload", "URI gambar tidak valid")
        }
    }

    companion object {
        const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }
}