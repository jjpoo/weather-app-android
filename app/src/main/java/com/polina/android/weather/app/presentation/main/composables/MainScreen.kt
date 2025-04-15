package com.polina.android.weather.app.presentation.main.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.polina.android.weather.app.presentation.main.state.MainUiState
import com.polina.android.weather.app.presentation.model.City
import com.polina.android.weather.app.utils.composables.ErrorScreen

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
                weatherInfo = requireNotNull(state.weatherInfo),
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
            CircularProgressIndicator(
                modifier = Modifier.size(56.dp),
                color = Color.Black,
                strokeWidth = 4.dp
            )
        }
    }
}
