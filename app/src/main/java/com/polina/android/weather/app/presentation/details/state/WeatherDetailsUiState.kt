package com.polina.android.weather.app.presentation.details.state

import com.polina.android.weather.app.domain.models.FiveDaysForecast
import com.polina.android.weather.app.presentation.model.TemperatureUnit

sealed class WeatherDetailsUiState {
    object Loading : WeatherDetailsUiState()

    data class Error(val message: String) : WeatherDetailsUiState()

    data class Success(
        val forecast: FiveDaysForecast,
        val cityName: String,
        val temperatureUnit: TemperatureUnit = TemperatureUnit.CELSIUS
    ) : WeatherDetailsUiState()
}