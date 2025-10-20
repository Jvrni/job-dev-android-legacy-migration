package com.goomer.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Menu(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String
)
