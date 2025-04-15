package com.polina.android.weather.app.presentation.details.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.polina.android.weather.app.R
import com.polina.android.weather.app.presentation.model.TemperatureUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    cityName: String,
    temperatureUnit: TemperatureUnit,
    onToggleUnit: () -> Unit,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = { "Forecast for $cityName" },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.deep_blue),
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }, actions = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Text(
                    text = "°C",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (temperatureUnit == TemperatureUnit.CELSIUS)
                        colorResource(R.color.white) else
                        colorResource(R.color.dark_blue).copy(alpha = 0.6f)
                )
                Switch(
                    checked = temperatureUnit == TemperatureUnit.FAHRENHEIT,
                    onCheckedChange = { onToggleUnit() },
                    modifier = Modifier.padding(horizontal = 8.dp),
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = colorResource(R.color.deep_blue).copy(alpha = 0.7f),
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = colorResource(R.color.deep_blue).copy(alpha = 0.7f),
                    )
                )
                Text(
                    text = "°F",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (temperatureUnit == TemperatureUnit.FAHRENHEIT)
                        Color.White else
                        colorResource(R.color.dark_blue).copy(alpha = 0.6f)
                )
            }
        }
    )
}

@Composable
@Preview
fun DetailsTopBarPreview() {
    DetailsTopBar(
        cityName = "Warsaw",
        temperatureUnit = TemperatureUnit.CELSIUS,
        onToggleUnit = {},
        onBackPressed = {}
    )
}