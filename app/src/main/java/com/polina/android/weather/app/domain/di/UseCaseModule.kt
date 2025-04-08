package com.polina.android.weather.app.domain.di

import com.polina.android.weather.app.domain.repository.WeatherRepository
import com.polina.android.weather.app.domain.usecase.GetCurrentWeatherUseCase
import com.polina.android.weather.app.domain.usecase.GetWeatherDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetCurrentWeatherUseCase(repository: WeatherRepository): GetCurrentWeatherUseCase {
        return GetCurrentWeatherUseCase(repository)
    }

    @Provides
    fun provideGetWeatherDetailsUseCase(repository: WeatherRepository): GetWeatherDetailsUseCase {
        return GetWeatherDetailsUseCase(repository)
    }
}