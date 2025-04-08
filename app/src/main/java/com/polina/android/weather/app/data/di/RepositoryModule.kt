package com.polina.android.weather.app.data.di

import com.polina.android.weather.app.data.api.WeatherApi
import com.polina.android.weather.app.data.repository.WeatherRepositoryImpl
import com.polina.android.weather.app.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Provides
    @Singleton
    fun provideApiKey(): String {
        return ""
//        return BuildConfig.WEATHER_API_KEY
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApi: WeatherApi, apiKey: String): WeatherRepository {
        return WeatherRepositoryImpl(weatherApi, apiKey)
    }
}