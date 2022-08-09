package com.mjay.instagram_clone.repositories

import com.mjay.instagram_clone.data.fake.FakeServicesImpl
import com.mjay.instagram_clone.models.Post
import com.mjay.instagram_clone.sealed.DataResponse
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val fakeServicesImpl: FakeServicesImpl,
) {

    suspend fun getFakePosts(): DataResponse<List<Post>> {
        return fakeServicesImpl.getFakePosts()
    }
}