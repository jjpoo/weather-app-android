package com.polina.android.weather.app.utils

import com.polina.android.weather.app.R
import com.polina.android.weather.app.domain.models.WeatherType

fun getWeatherIcon(weatherType: WeatherType): Int {
    return when (weatherType) {
        WeatherType.CLEAR -> R.drawable.ic_sunny
        WeatherType.CLOUDS -> R.drawable.ic_cloudy
        WeatherType.RAIN, WeatherType.DRIZZLE -> R.drawable.ic_drop
        WeatherType.THUNDERSTORM -> R.drawable.ic_thunder
        WeatherType.SNOW -> R.drawable.ic_snowy
        WeatherType.MIST -> R.drawable.ic_pressure
        else -> R.drawable.ic_sunny
    }
}