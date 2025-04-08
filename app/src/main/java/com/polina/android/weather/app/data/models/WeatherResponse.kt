package com.polina.android.weather.app.data.models

import com.squareup.moshi.Json

data class WeatherResponse(
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
    @field:Json(name = "name")
    val city: String
)