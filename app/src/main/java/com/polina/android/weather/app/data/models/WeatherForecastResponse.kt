package com.polina.android.weather.app.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherForecastResponse(
    val list: List<ForecastItem>
)

@JsonClass(generateAdapter = true)
data class ForecastItem(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    @field:Json(name = "dt_txt")
    val dtTxt: String
)