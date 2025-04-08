package com.polina.android.weather.app.domain.repository

import com.polina.android.weather.app.domain.models.FiveDaysForecast
import com.polina.android.weather.app.domain.models.WeatherInfo
import com.polina.android.weather.app.utils.Result as Result

interface WeatherRepository {
    suspend fun getWeatherForToday(city: String): Result<WeatherInfo>
    suspend fun getFiveDayForecast(city: String): Result<FiveDaysForecast>
}