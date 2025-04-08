package com.polina.android.weather.app.domain.usecase

import com.polina.android.weather.app.domain.models.WeatherInfo
import com.polina.android.weather.app.domain.repository.WeatherRepository
import com.polina.android.weather.app.utils.Result
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(cityName: String): Result<WeatherInfo> {
        return weatherRepository.getWeatherForToday(cityName)
    }
}