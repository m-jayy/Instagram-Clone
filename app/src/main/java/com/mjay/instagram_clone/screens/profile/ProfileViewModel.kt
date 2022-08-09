package com.mjay.instagram_clone.screens.profile


import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mjay.instagram_clone.UserSession
import com.mjay.instagram_clone.models.Featured
import com.mjay.instagram_clone.models.User
import com.mjay.instagram_clone.repositories.UserRepository
import com.mjay.instagram_clone.sealed.DataResponse
import com.mjay.instagram_clone.sealed.Error
import com.mjay.instagram_clone.sealed.Tab
import com.mjay.instagram_clone.sealed.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * A View model with hiltViewModel annotation that is used to access this view model everywhere needed
 */
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    val uiState = mutableStateOf<UiState>(UiState.Idle)
    val user = mutableStateOf<User?>(null)
    val activeTab = mutableStateOf<Tab>(Tab.Posts)
    val featuredStories: MutableList<Featured> = mutableStateListOf()

    fun updateActiveTab(newActiveTab: Tab) {
        activeTab.value = newActiveTab
    }

    fun getUserDetails() {
        UserSession.user?.let {
            user.value = it
        }
    }

    suspend fun getFakeFeatured() {
        if (featuredStories.isNotEmpty()) return
        uiState.value = UiState.Loading

        userRepository.getFakeFeaturedStories().let { dataResponse ->
            when (dataResponse) {
                is DataResponse.Success -> {
                    /** Got a response from the server successfully */
                    uiState.value = UiState.Success
                    dataResponse.data?.let {
                        featuredStories.addAll(it)
                    }
                }
                is DataResponse.Error -> {
                    /** An error happened when fetching data from the server */
                    uiState.value = UiState.Error(error = dataResponse.error ?: Error.Network)
                }
            }
        }
    }
}