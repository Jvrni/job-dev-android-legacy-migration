package com.goomer.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Menu(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String
): Parcelable
