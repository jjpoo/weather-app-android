package com.polina.android.weather.app.presentation.main.composables

import androidx.compose.runtime.Composable
import com.polina.android.weather.app.presentation.main.MainUiState
import com.polina.android.weather.app.presentation.model.City

@Composable
fun MainScreen(
    state: MainUiState,
    onCitySelected: (City) -> Unit
) {
    when (state) {

        is MainUiState.Success -> {

            TopBar(title = "Sunny Weather")
            CityPager(
                cities = state.availableCities,
                selectedCity = state.selectedCity,
                onCitySelected = onCitySelected
            )
        }

        is MainUiState.Error -> {

        }

        is MainUiState.Loading -> {

        }
    }
}