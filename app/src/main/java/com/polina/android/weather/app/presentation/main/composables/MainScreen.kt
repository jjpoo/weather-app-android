package com.polina.android.weather.app.presentation.main.composables

import androidx.compose.runtime.Composable
import com.polina.android.weather.app.presentation.main.state.MainUiState
import com.polina.android.weather.app.presentation.model.City
import com.polina.android.weather.app.utils.composables.ErrorScreen
import com.polina.android.weather.app.utils.composables.LoadingScreen

@Composable
fun MainScreen(
    state: MainUiState,
    onCitySelected: (City) -> Unit,
    onDetailsClick: () -> Unit,
    onRetry: () -> Unit,
    onBackToMain: () -> Unit
) {
    when (state) {

        is MainUiState.Success -> {
            DailyForecastScreen(
                header = state.weatherTitle,
                cities = state.availableCities,
                selectedCity = state.selectedCity,
                weatherInfo = state.weatherInfo,
                onCitySelected = onCitySelected,
                onDetailsClick = onDetailsClick
            )
        }

        is MainUiState.Error -> {
            ErrorScreen(
                errorMessage = state.message,
                onRetry = onRetry,
                onBackToMain = onBackToMain
            )
        }

        is MainUiState.Loading -> {
            LoadingScreen()
        }
    }
}
