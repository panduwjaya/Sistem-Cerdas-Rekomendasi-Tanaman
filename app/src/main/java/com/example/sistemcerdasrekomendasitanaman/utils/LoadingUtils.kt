package com.example.sistemcerdasrekomendasitanaman.utils

import android.view.View
import android.widget.ProgressBar

fun ProgressBar.show(state: Boolean) {
    visibility = if (state) View.VISIBLE else View.GONE
}