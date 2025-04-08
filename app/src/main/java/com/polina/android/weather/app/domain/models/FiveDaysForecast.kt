package com.polina.android.weather.app.domain.models

data class FiveDaysForecast(
    val cityName: String,
    val dailyForecasts: List<DailyWeatherForecast>
)
