package com.mjay.instagram_clone.sealed

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mjay.instagram_clone.R

sealed class Tab(@StringRes val title: Int, @DrawableRes val icon: Int){
    object Posts: Tab(title = R.string.posts, icon = R.drawable.posts_grid)
    object Tags: Tab(title = R.string.tags, icon = R.drawable.profile_tags)
}
