package com.mjay.instagram_clone.sealed

sealed class Orientation {
    object Vertical : Orientation()
    object Horizontal : Orientation()
}