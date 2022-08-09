package com.mjay.instagram_clone.screens.home


import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjay.instagram_clone.models.Post
import com.mjay.instagram_clone.models.Story
import com.mjay.instagram_clone.repositories.PostRepository
import com.mjay.instagram_clone.repositories.StoryRepository
import com.mjay.instagram_clone.sealed.DataResponse
import com.mjay.instagram_clone.sealed.Error
import com.mjay.instagram_clone.sealed.UiState
import com.mjay.instagram_clone.utils.appendOrRemove
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A View model with hiltViewModel annotation that is used to access this view model everywhere needed
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storyRepository: StoryRepository,
    private val postRepository: PostRepository,
) : ViewModel() {
    val storiesUiState = mutableStateOf<UiState>(UiState.Idle)
    val stories: MutableList<Story> = mutableStateListOf()

    val postsUiState = mutableStateOf<UiState>(UiState.Idle)
    val posts: MutableList<Post> = mutableStateListOf()

    val bookmarkedPostsIds = mutableStateListOf<Int>()
    val likedPostsIds = mutableStateListOf<Int>()

    fun getStories() {
        if (storiesUiState.value is UiState.Success) return

        storiesUiState.value = UiState.Loading
        viewModelScope.launch {
            /** Getting the stories from the fake repository */
            storyRepository.getStories().let { response ->
                when (response) {
                    is DataResponse.Success -> {
                        /** Got stories successfully */
                        storiesUiState.value = UiState.Success
                        response.data?.let { responseStories ->
                            stories.addAll(responseStories)
                        }
                    }
                    else -> {
                        /** Failed to get the stories, we should inform the UI */
                        storiesUiState.value =
                            UiState.Error(error = response.error ?: Error.Network)
                    }
                }
            }
        }
    }

    fun getPosts() {
        if (postsUiState.value is UiState.Success) return
        postsUiState.value = UiState.Loading
        viewModelScope.launch {
            /** Wait for 4 seconds to simulate the loading from server process */
            delay(4000)
            postRepository.getFakePosts().let { response ->
                when (response) {
                    is DataResponse.Success -> {
                        /** Got posts successfully */
                        postsUiState.value = UiState.Success
                        response.data?.let { responsePosts ->
                            posts.addAll(responsePosts)
                        }
                    }
                    else -> {
                        /** Failed to get the posts, we should inform the UI */
                        postsUiState.value =
                            UiState.Error(error = response.error ?: Error.Network)
                    }
                }
            }
        }
    }

    fun updateLikedPosts(id: Int) {
        likedPostsIds.appendOrRemove(element = id)
    }

    fun updateBookmarkedPosts(id: Int) {
        bookmarkedPostsIds.appendOrRemove(element = id)
    }

}