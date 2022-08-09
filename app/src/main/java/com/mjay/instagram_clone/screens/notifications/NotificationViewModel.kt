package com.mjay.instagram_clone.screens.notifications


import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjay.instagram_clone.data.fake.FakeServicesImpl
import com.mjay.instagram_clone.models.MyNotification
import com.mjay.instagram_clone.sealed.DataResponse
import com.mjay.instagram_clone.sealed.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * A View model with hiltViewModel annotation that is used to access this view model everywhere needed
 */
@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val fakeServicesImpl: FakeServicesImpl,
) : ViewModel() {
    val uiState = mutableStateOf<UiState>(UiState.Idle)
    val notifications: MutableList<MyNotification> = mutableStateListOf()
    val todayDateAsLong  = Date().time

    fun getMyNotifications() {
        if(uiState.value == UiState.Success) return

        uiState.value = UiState.Loading
        viewModelScope.launch {
            fakeServicesImpl.getFakeNotifications().let { dataResponse ->
                when (dataResponse) {
                    is DataResponse.Success -> {
                        /** Got a response from the server successfully */
                        uiState.value = UiState.Success
                        dataResponse.data?.let {
                            notifications.addAll(it)
                        }
                    }
                    is DataResponse.Error -> {
                        /** An error happened when fetching data from the server */
                    }
                }
            }
        }
    }
}