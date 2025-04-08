package com.polina.android.weather.app.presentation.model

import androidx.annotation.DrawableRes

data class City(
    val name: String,
    @DrawableRes val iconRes: Int
)
