package com.polina.android.weather.app.presentation.main.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.polina.android.weather.app.domain.models.WeatherInfo
import com.polina.android.weather.app.presentation.model.City
import com.polina.android.weather.app.presentation.theme.DarkBlue

@Composable
fun DailyForecastScreen(
    header: String,
    cities: List<City>,
    selectedCity: City,
    weatherInfo: WeatherInfo,
    onCitySelected: (City) -> Unit,
    onDetailsClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(title = header)
        },
        bottomBar = {
            BottomBar(onDetailsClick)
        },
        content = {
            MainContent(
                modifier = Modifier.padding(it),
                cities = cities,
                selectedCity = selectedCity,
                weatherInfo = weatherInfo,
                onCitySelected = onCitySelected
            )
        }
    )
}