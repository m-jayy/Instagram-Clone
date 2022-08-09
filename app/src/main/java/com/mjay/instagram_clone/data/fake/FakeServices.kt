package com.mjay.instagram_clone.data.fake

import com.mjay.instagram_clone.models.Chat
import com.mjay.instagram_clone.models.Featured
import com.mjay.instagram_clone.models.MyNotification
import com.mjay.instagram_clone.models.Post
import com.mjay.instagram_clone.models.Story
import com.mjay.instagram_clone.models.User
import com.mjay.instagram_clone.sealed.DataResponse

interface FakeServices {
    suspend fun signInUser(email: String, password: String): DataResponse<User>

    suspend fun getFakePosts(): DataResponse<List<Post>>

    suspend fun getFakeStories(): DataResponse<List<Story>>

    suspend fun getFakeFeaturedStories(): DataResponse<List<Featured>>

    suspend fun findStoryById(storyId: Int): DataResponse<Story?>

    suspend fun findPostById(postId: Int): DataResponse<Post?>

    suspend fun getFakeUsers(userName: String): DataResponse<List<User>>

    suspend fun findUserByUsername(userName: String): DataResponse<User?>

    suspend fun getFakeNotifications(): DataResponse<List<MyNotification>>

    suspend fun getFakeChats() : DataResponse<List<Chat>>

    suspend fun getFakeOnlineUsers(): DataResponse<List<User>>
}