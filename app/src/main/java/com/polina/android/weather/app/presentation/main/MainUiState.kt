package com.polina.android.weather.app.presentation.main

import com.polina.android.weather.app.domain.models.WeatherInfo
import com.polina.android.weather.app.presentation.model.City

sealed class MainUiState {
    object Loading : MainUiState()
    data class Error(val message: String) : MainUiState()
    data class Success(
        val weatherInfo: WeatherInfo,
        val selectedCity: City,
        val availableCities: List<City>,
        val weatherTitle: String = "Weather Forecast App"
    ) : MainUiState()
}
