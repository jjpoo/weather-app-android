package com.polina.android.weather.app.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
    @field:Json(name = "name")
    val city: String
)