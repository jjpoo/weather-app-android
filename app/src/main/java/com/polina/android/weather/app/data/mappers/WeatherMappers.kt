package com.polina.android.weather.app.data.mappers

import com.polina.android.weather.app.data.models.ForecastItem
import com.polina.android.weather.app.data.models.WeatherForecastResponse
import com.polina.android.weather.app.data.models.WeatherResponse
import com.polina.android.weather.app.domain.models.DailyWeatherForecast
import com.polina.android.weather.app.domain.models.FiveDaysForecast
import com.polina.android.weather.app.domain.models.WeatherInfo
import com.polina.android.weather.app.domain.models.WeatherType
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun WeatherResponse.toDailyWeatherInfo(): WeatherInfo {
    return WeatherInfo(
        cityName = city,
        temperature = main.temp,
        feelsLike = main.feelsLike,
        minTemperature = main.minTemp,
        maxTemperature = main.maxTemp,
        humidity = main.humidity,
        weatherType = WeatherType.fromResponseWeatherType(
            weather.firstOrNull()?.main ?: ""
        ),
        windSpeed = wind.speed,
        pressure = main.pressure,
        description = weather.firstOrNull()?.description ?: "Just a day",
        main = weather.firstOrNull()?.main ?: "Just a day"
    )
}

fun WeatherForecastResponse.toFiveDaysForecast(
    cityName: String
): FiveDaysForecast {

    val groupedForecasts = groupedForecastsByDay(this.list)
    val dailyForecasts = groupedForecasts.map { (date, forecasts) ->
        val minTemp = forecasts.minOfOrNull { it.main.minTemp } ?: 0.0
        val maxTemp = forecasts.maxOfOrNull { it.main.maxTemp } ?: 0.0
        val middayForecast = forecasts.minByOrNull {
            val hour = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                .parse(it.dtTxt)?.let { date ->
                    Calendar.getInstance().apply { time = date }.get(Calendar.HOUR_OF_DAY)
                } ?: 0
            Math.abs(hour - 12)
        } ?: forecasts.first()

        DailyWeatherForecast(
            date = date,
            minTemp = minTemp,
            maxTemp = maxTemp,
            weatherType = mapWeatherTypeFromResponse(
                middayForecast.weather.firstOrNull()?.main ?: ""
            ),
            weatherDescription = middayForecast.weather.firstOrNull()?.description ?: "",
            iconCode = middayForecast.weather.firstOrNull()?.icon ?: ""
        )
    }.sortedBy { it.date }

    return FiveDaysForecast(
        cityName = cityName,
        dailyForecasts = dailyForecasts
    )
}

private fun groupedForecastsByDay(forecasts: List<ForecastItem>): Map<Long, List<ForecastItem>> {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    return forecasts.groupBy { forecastItem ->
        try {
            val forecastDate = dateFormat.parse(forecastItem.dtTxt) ?: Date()

            val calendar = Calendar.getInstance()
            calendar.time = forecastDate
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            calendar.timeInMillis
        } catch (e: Exception) {
            System.currentTimeMillis()
        }
    }
}

private fun mapWeatherTypeFromResponse(weatherMain: String): WeatherType {
    return WeatherType.fromResponseWeatherType(weatherMain)
}