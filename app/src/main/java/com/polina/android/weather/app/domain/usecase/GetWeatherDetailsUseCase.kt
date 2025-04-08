package com.polina.android.weather.app.domain.usecase

import com.polina.android.weather.app.domain.models.FiveDaysForecast
import com.polina.android.weather.app.domain.repository.WeatherRepository
import com.polina.android.weather.app.utils.Result
import javax.inject.Inject

class GetWeatherDetailsUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(cityName: String): Result<FiveDaysForecast> {
        return weatherRepository.getFiveDayForecast(cityName)
    }
}