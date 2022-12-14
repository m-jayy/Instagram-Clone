package com.mjay.instagram_clone.models

data class Post(
    val id: Int,
    var user: User,
    val location: String,
    val images: List<Int>,
    val caption: String = "Good Morning",
    val reacts: PostReacts,
    val date: String = "2 days ago",
    val likedByMe: Boolean = false,
){

}

data class PostReacts(
    val recentUser: User,
    val othersCount: Int,
)
