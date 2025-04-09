package com.polina.android.weather.app.presentation.details.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.polina.android.weather.app.domain.models.DailyWeatherForecast
import com.polina.android.weather.app.domain.models.WeatherType
import com.polina.android.weather.app.domain.models.WeatherType.Companion.fromResponseWeatherType
import com.polina.android.weather.app.presentation.model.TemperatureUnit
import com.polina.android.weather.app.utils.getDayOfWeek
import com.polina.android.weather.app.utils.getFormattedDate
import com.polina.android.weather.app.utils.getWeatherIcon
import java.util.Calendar

@Composable
fun DailyForecastCard(
    forecast: DailyWeatherForecast,
    temperatureUnit: TemperatureUnit,
    convertTemp: (Double) -> Double,
    modifier: Modifier = Modifier
) {
    val unitSymbol = if (temperatureUnit == TemperatureUnit.CELSIUS) "°C" else "°F"

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = getDayOfWeek(forecast.date),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Icon(
                            painter = painterResource(
                                getWeatherIcon(
                                    fromResponseWeatherType(
                                        forecast.weatherDescription
                                    )
                                )
                            ),
                            contentDescription = "Weather Icon",
                            Modifier.size(40.dp)
                        )
                    }

                    Text(
                        text = getFormattedDate(forecast.date),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )

                    Text(
                        text = forecast.weatherDescription.uppercase(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontStyle = FontStyle.Italic
                    )

                    Text(
                        text = "Minimum temperature: ${convertTemp(forecast.minTemp).toInt()}$unitSymbol",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ForecastCardPreview() {

    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 12)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    fun getTimestampForDaysFromNow(daysFromNow: Int): Long {
        val cal = calendar.clone() as Calendar
        cal.add(Calendar.DAY_OF_YEAR, daysFromNow)
        return cal.timeInMillis / 1000
    }

    val dailyForecast = DailyWeatherForecast(
        date = getTimestampForDaysFromNow(0),
        maxTemp = 7.5,
        minTemp = 0.2,
        weatherType = WeatherType.CLEAR,
        iconCode = "",
        weatherDescription = "cloudy"
    )

    DailyForecastCard(
        forecast = dailyForecast,
        temperatureUnit = TemperatureUnit.CELSIUS,
        convertTemp = { it }
    )
}