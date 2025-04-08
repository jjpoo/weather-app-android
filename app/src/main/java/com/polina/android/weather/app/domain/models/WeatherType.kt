package com.polina.android.weather.app.domain.models

enum class WeatherType {
    CLEAR,
    CLOUDS,
    RAIN,
    DRIZZLE,
    THUNDERSTORM,
    SNOW,
    MIST,
    FOG,
    UNKNOWN;

    companion object {
        fun fromResponseWeatherType(responseWeatherType: String): WeatherType {
            return when (responseWeatherType) {
                "Clear" -> CLEAR
                "Clouds" -> CLOUDS
                "Rain" -> RAIN
                "Drizzle" -> DRIZZLE
                "Thunderstorm" -> THUNDERSTORM
                "Snow" -> SNOW
                "Mist" -> MIST
                "Fog" -> FOG
                else -> UNKNOWN
            }
        }
    }
}