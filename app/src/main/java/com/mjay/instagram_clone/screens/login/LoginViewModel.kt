package com.mjay.instagram_clone.screens.login


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjay.instagram_clone.R
import com.mjay.instagram_clone.UserSession
import com.mjay.instagram_clone.models.User
import com.mjay.instagram_clone.repositories.UserRepository
import com.mjay.instagram_clone.sealed.DataResponse
import com.mjay.instagram_clone.sealed.Error
import com.mjay.instagram_clone.sealed.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A View model with hiltViewModel annotation that is used to access this view model everywhere needed
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    val uiState = mutableStateOf<UiState>(UiState.Idle)
    val recentUser = User(
        userId = 1,
        userName = "Mohsin Javed",
        profile = R.drawable.my_profile,
    )
    fun authenticateUser(onAuthenticated: () -> Unit, onAuthenticationFailed: () -> Unit) {
        uiState.value = UiState.Loading
        /** We will use the coroutine so that we don't block our dear : The UiThread */
        viewModelScope.launch {
            userRepository.signInUser(
                email = "mustaphathegreat@gmail.com",
                password = "fakepasswordyoudumb",
            ).let {
                when (it) {
                    is DataResponse.Success -> {
                        it.data?.let {
                            /** Authenticated successfully */
                            uiState.value = UiState.Success
                            UserSession.user = it
                            onAuthenticated()
                        }
                    }
                    is DataResponse.Error -> {
                        /** An error occurred while authenticating */
                        uiState.value = UiState.Error(error = it.error ?: Error.Network)
                        onAuthenticationFailed()
                    }
                }
            }
        }
    }
}