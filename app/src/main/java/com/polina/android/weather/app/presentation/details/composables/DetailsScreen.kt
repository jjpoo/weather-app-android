package com.polina.android.weather.app.presentation.details.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.polina.android.weather.app.presentation.details.state.WeatherDetailsUiState
import com.polina.android.weather.app.utils.composables.ErrorScreen

@Composable
fun DetailsScreen(
    cityName: String,
    state: WeatherDetailsUiState,
    onToggleUnit: () -> Unit,
    onBackPressed: () -> Unit,
    convertTemperature: (Double) -> Double,
    onRetry: () -> Unit,
    onBackToMain: () -> Unit
) {

    when (state) {
        is WeatherDetailsUiState.Success -> {
            Scaffold(
                topBar = {
                    DetailsTopBar(
                        cityName = cityName,
                        temperatureUnit = state.temperatureUnit,
                        onToggleUnit = onToggleUnit,
                        onBackPressed = onBackPressed
                    )
                }
            ) {
                DetailsContent(
                    modifier = Modifier.padding(it),
                    forecast = state.forecast,
                    temperatureUnit = state.temperatureUnit,
                    convertTemp = convertTemperature
                )
            }
        }

        is WeatherDetailsUiState.Error -> {
            ErrorScreen(
                errorMessage = state.message,
                onRetry = onRetry,
                onBackToMain = onBackToMain
            )
        }

        is WeatherDetailsUiState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier.size(56.dp),
                color = Color.Black,
                strokeWidth = 4.dp
            )
        }
    }
}