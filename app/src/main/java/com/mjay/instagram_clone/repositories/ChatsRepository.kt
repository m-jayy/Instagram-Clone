package com.mjay.instagram_clone.repositories

import com.mjay.instagram_clone.data.fake.FakeServicesImpl
import com.mjay.instagram_clone.models.Chat
import com.mjay.instagram_clone.models.User
import com.mjay.instagram_clone.sealed.DataResponse
import javax.inject.Inject

class ChatsRepository @Inject constructor(
    private val fakeServicesImpl: FakeServicesImpl,
) {
    suspend fun getFakeChats() : DataResponse<List<Chat>>{
        return fakeServicesImpl.getFakeChats()
    }

    suspend fun getFakeOnlineUsers() : DataResponse<List<User>>{
        return fakeServicesImpl.getFakeOnlineUsers()
    }
}