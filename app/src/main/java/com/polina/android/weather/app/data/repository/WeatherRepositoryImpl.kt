package com.polina.android.weather.app.data.repository

import com.polina.android.weather.app.data.mappers.toDailyWeatherInfo
import com.polina.android.weather.app.data.mappers.toFiveDaysForecast
import com.polina.android.weather.app.data.api.WeatherApi
import com.polina.android.weather.app.domain.models.FiveDaysForecast
import com.polina.android.weather.app.domain.models.WeatherInfo
import com.polina.android.weather.app.domain.repository.WeatherRepository
import com.polina.android.weather.app.utils.Result
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val apiKey: String
) : WeatherRepository {

//    private val apiKey: String = "ec4d5876245ec98d475b5312154f4247"
    private val units: String = "metric"

    override suspend fun getWeatherForToday(city: String): Result<WeatherInfo> {
        return try {
            Result.Success(
                data = weatherApi.getWeatherDataForToday(city, apiKey, units).toDailyWeatherInfo()
            )
        } catch (e: Exception) {
            Result.Error(
                message = e.message ?: "Unknown error occurred"
            )
        }
    }

    override suspend fun getFiveDayForecast(city: String): Result<FiveDaysForecast> {
        return try {
            Result.Success(
                data = weatherApi.getWeatherDataForFiveDays(city, apiKey, units)
                    .toFiveDaysForecast(cityName = city)
            )
        } catch (e: Exception) {
            Result.Error(
                message = e.message ?: "Unknown error occurred"
            )
        }
    }
}