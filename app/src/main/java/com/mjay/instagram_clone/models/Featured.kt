package com.mjay.instagram_clone.models

data class Featured(
    val id: Int,
    val title: String,
    val user: User,
    val images: List<Int>,
)
