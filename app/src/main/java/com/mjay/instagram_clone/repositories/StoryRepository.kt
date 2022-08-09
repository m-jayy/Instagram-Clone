package com.mjay.instagram_clone.repositories

import com.mjay.instagram_clone.data.fake.FakeServicesImpl
import com.mjay.instagram_clone.models.Story
import com.mjay.instagram_clone.sealed.DataResponse
import javax.inject.Inject

class StoryRepository @Inject constructor(
    private val fakeServicesImpl: FakeServicesImpl,
) {
    suspend fun getStories(): DataResponse<List<Story>> {
        return fakeServicesImpl.getFakeStories()
    }
}