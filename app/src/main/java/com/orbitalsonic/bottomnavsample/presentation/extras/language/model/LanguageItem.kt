package com.orbitalsonic.bottomnavsample.presentation.extras.language.model

import androidx.annotation.DrawableRes
import androidx.annotation.Keep

@Keep
data class LanguageItem(
    val languageCode: String,
    val languageShortName: String,
    val languageFullName: String,
    @DrawableRes val flag: Int,
    val selected: Boolean
)