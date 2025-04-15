package com.polina.android.weather.app.presentation.main.state

import com.polina.android.weather.app.domain.models.WeatherInfo
import com.polina.android.weather.app.presentation.model.City

sealed class MainUiState {
    object Loading : MainUiState()
    data class Error(val message: String) : MainUiState()
    data class Success(
        val weatherInfo: WeatherInfo? = null,
        val selectedCity: City = City("Warsaw", 0),
        val availableCities: List<City> = listOf(),
        val weatherTitle: String = "Weather Forecast App"
    ) : MainUiState()
}
