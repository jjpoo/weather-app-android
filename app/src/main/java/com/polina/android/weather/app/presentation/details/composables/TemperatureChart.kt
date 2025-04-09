package com.polina.android.weather.app.presentation.details.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.graphics.Paint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedCard
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.polina.android.weather.app.domain.models.DailyWeatherForecast
import com.polina.android.weather.app.domain.models.FiveDaysForecast
import com.polina.android.weather.app.domain.models.WeatherType
import com.polina.android.weather.app.presentation.model.TemperatureUnit
import com.polina.android.weather.app.utils.theme.DarkBlue
import com.polina.android.weather.app.utils.theme.DeepBlue
import com.polina.android.weather.app.utils.getDayOfWeek
import java.util.Calendar
import kotlin.math.roundToInt

@Composable
fun TemperatureChart(
    forecast: FiveDaysForecast,
    temperatureUnit: TemperatureUnit,
    convertTemp: (Double) -> Double,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(2.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Canvas(modifier = modifier.padding(10.dp)) {
            if (forecast.dailyForecasts.isEmpty()) return@Canvas

            val dailyForecasts = forecast.dailyForecasts.take(5)
            val maxTemp = dailyForecasts.maxOf { convertTemp(it.maxTemp) }
            val minTemp =
                dailyForecasts.minOf { convertTemp(it.maxTemp) }.coerceAtMost(maxTemp - 5)
            val temperatureRange = (maxTemp - minTemp).coerceAtLeast(1.0)

            val width = size.width
            val height = size.height
            val padding = 50f
            val graphWidth = width - 2 * padding
            val graphHeight = height - 2 * padding

            val points = dailyForecasts.mapIndexed { index, forecast ->
                val x = padding + index * graphWidth / (dailyForecasts.size - 1).toFloat()
                val normalizedTemp = (convertTemp(forecast.maxTemp) - minTemp) / temperatureRange
                val y = height - padding - (normalizedTemp * graphHeight).toFloat()
                Offset(x, y)
            }

            for (i in 0..4) {
                val y = padding + i + graphHeight / 4
                val temp = maxTemp - (i * temperatureRange / 4)

                drawContext.canvas.nativeCanvas.drawText(
                    "${temp.roundToInt()}°",
                    padding - 25f,
                    y + 5f,
                    Paint().apply {
                        color = DeepBlue.toArgb()
                        textAlign = Paint.Align.RIGHT
                        textSize = 0.sp.toPx()
                    }
                )

                if (points.size > 1) {
                    for (i in 0 until points.size - 1) {
                        drawLine(
                            color = DeepBlue,
                            start = points[i],
                            end = points[i + 1],
                            strokeWidth = 2.dp.toPx()
                        )
                    }
                }

                if (points.size > 1) {
                    for (i in 0 until points.size - 1) {
                        drawLine(
                            color = DarkBlue,
                            start = points[i],
                            end = points[i + 1],
                            strokeWidth = 1.dp.toPx()
                        )
                    }
                }

                val unitSymbol = if (temperatureUnit == TemperatureUnit.CELSIUS) "°C" else "°F"

                points.forEachIndexed { index, point ->
                    drawCircle(
                        color = DeepBlue,
                        radius = 4.dp.toPx(),
                        center = point
                    )
                    drawContext.canvas.nativeCanvas.drawText(
                        "${convertTemp(dailyForecasts[index].maxTemp).roundToInt()}$unitSymbol",
                        point.x,
                        point.y - 12.dp.toPx(),
                        Paint().apply {
                            color = DeepBlue.toArgb()
                            textAlign = Paint.Align.CENTER
                            textSize = 12.sp.toPx()
                        }
                    )
                    drawContext.canvas.nativeCanvas.drawText(
                        getDayOfWeek(dailyForecasts[index].date),
                        point.x,
                        height - 3.dp.toPx(),
                        Paint().apply {
                            color = DeepBlue.toArgb()
                            textAlign = Paint.Align.CENTER
                            textSize = 12.sp.toPx()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TemperatureChartPreview() {
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

    val dailyForecast = listOf(
        DailyWeatherForecast(
            date = getTimestampForDaysFromNow(0),
            maxTemp = 2.5,
            minTemp = 8.2,
            weatherType = WeatherType.CLEAR,
            iconCode = "",
            weatherDescription = "cloudy"
        ),
        DailyWeatherForecast(
            date = getTimestampForDaysFromNow(0),
            maxTemp = 5.0,
            minTemp = 6.2,
            weatherType = WeatherType.CLEAR,
            iconCode = "",
            weatherDescription = "cloudy"
        ),
        DailyWeatherForecast(
            date = getTimestampForDaysFromNow(0),
            maxTemp = 5.5,
            minTemp = 1.2,
            weatherType = WeatherType.CLEAR,
            iconCode = "",
            weatherDescription = "cloudy"
        ),
        DailyWeatherForecast(
            date = getTimestampForDaysFromNow(0),
            maxTemp = 5.3,
            minTemp = -1.2,
            weatherType = WeatherType.CLEAR,
            iconCode = "",
            weatherDescription = "cloudy"
        ),
        DailyWeatherForecast(
            date = getTimestampForDaysFromNow(0),
            maxTemp = 7.5,
            minTemp = 0.2,
            weatherType = WeatherType.CLEAR,
            iconCode = "",
            weatherDescription = "cloudy"
        )
    )
    val mockFiveDaysForecast = FiveDaysForecast(
        cityName = "Warsaw",
        dailyForecasts = dailyForecast
    )
    TemperatureChart(
        forecast = mockFiveDaysForecast,
        temperatureUnit = TemperatureUnit.CELSIUS,
        convertTemp = { it },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}