package com.polina.android.weather.app.presentation.details.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polina.android.weather.app.domain.usecase.GetWeatherDetailsUseCase
import com.polina.android.weather.app.presentation.model.TemperatureUnit
import com.polina.android.weather.app.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getWeatherDetailsUseCase: GetWeatherDetailsUseCase
) : ViewModel() {

    val _state = MutableStateFlow<WeatherDetailsUiState>(WeatherDetailsUiState.Loading)
    val state = _state.asStateFlow()

    fun loadForecast(cityName: String) {
        viewModelScope.launch {
            _state.value = WeatherDetailsUiState.Loading

            when (val result = getWeatherDetailsUseCase(cityName)) {
                is Result.Success -> {
                    _state.value = WeatherDetailsUiState.Success(
                        forecast = requireNotNull(result.data),
                        cityName = cityName
                    )
                }

                is Result.Error -> {
                    _state.value = WeatherDetailsUiState.Error(
                        message = result.error ?: "Unknown error occurred"
                    )
                }
            }
        }
    }

    fun toggleTemperatureUnit() {
        val currentState = _state.value
        if (currentState is WeatherDetailsUiState.Success) {
            _state.value = currentState.copy(
                temperatureUnit = when (currentState.temperatureUnit) {
                    TemperatureUnit.CELSIUS -> TemperatureUnit.FAHRENHEIT
                    TemperatureUnit.FAHRENHEIT -> TemperatureUnit.CELSIUS
                }
            )
        }
    }

    fun convertTemperature(celsius: Double): Double {
        val currentState = _state.value
        return if (currentState is WeatherDetailsUiState.Success &&
            currentState.temperatureUnit == TemperatureUnit.FAHRENHEIT
        ) {
            celsius * 9 / 5 + 32
        } else {
            celsius
        }
    }
}