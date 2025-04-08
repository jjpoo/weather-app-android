package com.polina.android.weather.app.presentation.main.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.polina.android.weather.app.domain.models.WeatherInfo
import com.polina.android.weather.app.presentation.model.City

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    cities: List<City>,
    selectedCity: City,
    weatherInfo: WeatherInfo,
    onCitySelected: (City) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        CityPager(
            cities = cities,
            selectedCity = selectedCity,
            onCitySelected = onCitySelected,
            modifier = Modifier.padding(top = 20.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        CurrentWeatherCard(weatherInfo = weatherInfo)
    }
}