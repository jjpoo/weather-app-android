package com.polina.android.weather.app.data.api

import com.polina.android.weather.app.data.models.WeatherForecastResponse
import com.polina.android.weather.app.data.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getWeatherDataForToday(
        @Query("q") city: String,
        @Query("appid") key: String,
        @Query("units") units: String
    ): WeatherResponse

    @GET("data/2.5/forecast")
    suspend fun getWeatherDataForFiveDays(
        @Query("q") city: String,
        @Query("appid") key: String,
        @Query("units") units: String
    ): WeatherForecastResponse
}