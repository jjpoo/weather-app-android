package com.polina.android.weather.app.data.models

import com.squareup.moshi.Json

data class WeatherForecastResponse(
    val list: List<ForecastItem>
)

data class ForecastItem(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    @field:Json(name = "dt_txt")
    val dtTxt: String
)