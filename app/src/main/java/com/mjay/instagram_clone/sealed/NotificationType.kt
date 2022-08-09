package com.mjay.instagram_clone.sealed

import com.mjay.instagram_clone.models.PostReacts
import com.mjay.instagram_clone.models.User

sealed class NotificationType {
    class FollowNotification(val user: User, val followed: Boolean) : NotificationType()

    class ReactsNotification(
        val postId: Int,
        val postCoverUrl: Int,
        val postReacts: PostReacts
    ) : NotificationType()

    class MentionNotification(
        val user: User,
        val postId: Int,
        val commentId: Int,
        val comment: String,
    ) : NotificationType()
}
