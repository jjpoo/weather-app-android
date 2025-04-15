package com.polina.android.weather.app.presentation.details.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.polina.android.weather.app.R
import com.polina.android.weather.app.domain.models.FiveDaysForecast
import com.polina.android.weather.app.presentation.model.TemperatureUnit

@Composable
fun DetailsContent(
    modifier: Modifier = Modifier,
    forecast: FiveDaysForecast,
    temperatureUnit: TemperatureUnit,
    convertTemp: (Double) -> Double
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.deep_blue))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Temperature",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            TemperatureChart(
                forecast = forecast,
                temperatureUnit = temperatureUnit,
                convertTemp = convertTemp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Daily Forecast",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(forecast.dailyForecasts) { dailyForecast ->
                    DailyForecastCard(
                        forecast = dailyForecast,
                        temperatureUnit = temperatureUnit,
                        convertTemp = convertTemp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}