package com.polina.android.weather.app.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polina.android.weather.app.domain.usecase.GetCurrentWeatherUseCase
import com.polina.android.weather.app.presentation.model.City
import com.polina.android.weather.app.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.polina.android.weather.app.R

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val cities = listOf(
        City("Warsaw", R.drawable.card_1),
        City("Wroclaw", R.drawable.card_1),
        City("Cracow", R.drawable.card_1)
    )

    private val _state = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val state = _state.asStateFlow()

    private val _selectedCity = MutableStateFlow(cities.first())
    val selectedCity = _selectedCity.asStateFlow()

    init {
        getWeatherForCurrentCity()
    }

    fun selectCity(city: City) {
        _selectedCity.value = city
        getWeatherForCurrentCity()
    }

    private fun getWeatherForCurrentCity() {
        viewModelScope.launch {
            _state.value = MainUiState.Loading

            when (val result = getCurrentWeatherUseCase(_selectedCity.value.name)) {
                is Result.Success -> {
                    _state.value = MainUiState.Success(
                        weatherInfo = requireNotNull(result.data),
                        selectedCity = _selectedCity.value,
                        availableCities = cities
                    )
                }

                is Result.Error -> {
                    _state.value = MainUiState.Error(
                        message = result.error ?: "Unknown error occurred"
                    )
                }
            }
        }
    }
}