package com.polina.android.weather.app.data.models

import com.squareup.moshi.Json

data class Main(
    val temp: Double,
    @field:Json(name = "feels_like")
    val feelsLike: Double,
    @field:Json(name = "temp_min")
    val minTemp: Double,
    @field:Json(name = "temp_max")
    val maxTemt: Double,
    @field:Json(name = "temp_max")
    val pressure: Int,
    val humidity: Int
)