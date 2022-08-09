package com.mjay.instagram_clone.sealed


sealed class NetworkStatus {
    object Connected : NetworkStatus()
    object Disconnected : NetworkStatus()
}
