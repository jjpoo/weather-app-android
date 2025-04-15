package com.polina.android.weather.app.presentation.main.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.polina.android.weather.app.R
import com.polina.android.weather.app.domain.models.WeatherInfo

@Composable
fun CurrentWeatherCard(
    weatherInfo: WeatherInfo
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.dark_blue).copy(alpha = 0.3f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${weatherInfo.temperature}°C",
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
                color = colorResource(R.color.dark_blue)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = weatherInfo.main,
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 40.sp,
                color = colorResource(R.color.dark_blue)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                WeatherDetailItem(
                    icon = painterResource(R.drawable.ic_drop),
                    label = "Humidity",
                    value = "${weatherInfo.humidity} %"
                )

                WeatherDetailItem(
                    icon = painterResource(R.drawable.ic_wind),
                    label = "Wind",
                    value = "${weatherInfo.windSpeed} m/s"
                )
            }
        }
    }
}

@Composable
fun WeatherDetailItem(
    icon: Painter, label: String, value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = icon,
            contentDescription = label,
            modifier = Modifier.size(50.dp),
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 25.sp,
            color = colorResource(R.color.dark_blue)
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            color = colorResource(R.color.dark_blue)
        )
    }
}