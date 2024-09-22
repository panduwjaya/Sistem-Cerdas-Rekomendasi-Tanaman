package com.example.sistemcerdasrekomendasitanaman.data.database

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailTanaman(
    var id: Int,
    var imageSlide: Int,
    var name: String,
    var synonim: String,
    var temperature: String,
    var description: String
) : Parcelable