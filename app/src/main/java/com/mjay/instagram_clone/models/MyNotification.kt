package com.mjay.instagram_clone.models

import com.mjay.instagram_clone.sealed.NotificationType

data class MyNotification(
    val id: Int,
    val type: NotificationType,
    val time: Long,
)
