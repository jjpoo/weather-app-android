package com.polina.android.weather.app.domain.models

data class WeatherInfo(
    val cityName: String,
    val temperature: Double,
    val feelsLike: Double,
    val minTemperature: Double,
    val maxTemperature: Double,
    val pressure: Int,
    val humidity: Int,
    val weatherType: WeatherType,
    val windSpeed: Double,
    val description: String,
    val main: String
)