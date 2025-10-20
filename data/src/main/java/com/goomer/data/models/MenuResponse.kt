package com.goomer.data.models

import android.os.Parcelable
import com.goomer.domain.models.Menu
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuResponse(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String
): Parcelable {
    fun toDomain(): Menu = Menu(
        id = id,
        name = name,
        description = description,
        price = price,
        imageUrl = imageUrl
    )
}
