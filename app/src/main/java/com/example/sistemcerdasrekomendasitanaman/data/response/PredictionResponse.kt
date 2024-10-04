package com.example.sistemcerdasrekomendasitanaman.data.response

import com.google.gson.annotations.SerializedName

data class PredictionResponse(

    @field:SerializedName("part")
    val part: String? = null,

    @field:SerializedName("plant")
    val plant: String? = null
)