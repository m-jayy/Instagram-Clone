package com.mjay.instagram_clone.sealed

import androidx.annotation.StringRes
import com.mjay.instagram_clone.R

sealed class Language(
    val code: String,
    @StringRes val title: Int,
){
    object Arabic: Language(code = "ar",title = R.string.arabic)
    object English: Language(code = "en",title = R.string.english)
}