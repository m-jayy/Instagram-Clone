package com.mjay.instagram_clone.repositories

import com.mjay.instagram_clone.data.fake.FakeServicesImpl
import com.mjay.instagram_clone.models.User
import com.mjay.instagram_clone.sealed.DataResponse
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val fakeServicesImpl: FakeServicesImpl,
) {
    suspend fun signInUser(email: String, password: String): DataResponse<User> {
        return fakeServicesImpl.signInUser(email = email, password = password)
    }

    suspend fun getFakeFeaturedStories() =
        fakeServicesImpl.getFakeFeaturedStories()
}