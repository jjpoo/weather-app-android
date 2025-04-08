package com.polina.android.weather.app.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Main(
    val temp: Double,
    @field:Json(name = "feels_like")
    val feelsLike: Double,
    @field:Json(name = "temp_min")
    val minTemp: Double,
    @field:Json(name = "temp_max")
    val maxTemp: Double,
    val pressure: Int,
    val humidity: Int
)