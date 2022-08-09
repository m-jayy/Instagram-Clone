package com.mjay.instagram_clone.models

data class Chat(
    val id: Int,
    val otherUser: User,
    val lastMessage: Message,
)
