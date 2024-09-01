package com.example.sistemcerdasrekomendasitanaman.data.network

import com.example.sistemcerdasrekomendasitanaman.data.response.PredictionResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("predict")
    fun predict(
        @Part file: MultipartBody.Part
    ): Call<PredictionResponse>
}