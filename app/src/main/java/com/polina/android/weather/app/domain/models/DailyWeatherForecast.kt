package com.polina.android.weather.app.domain.models

data class DailyWeatherForecast(
    val date: Long,
    val minTemp: Double,
    val maxTemp: Double,
    val weatherType: WeatherType,
    val weatherDescription: String,
    val iconCode: String
)
