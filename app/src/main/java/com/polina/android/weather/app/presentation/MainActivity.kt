package com.polina.android.weather.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.polina.android.weather.app.presentation.details.state.DetailsViewModel
import com.polina.android.weather.app.presentation.main.state.MainViewModel
import com.polina.android.weather.app.presentation.navigation.WeatherNavHost
import com.polina.android.weather.app.utils.theme.AndroidweatherappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainViewModel by viewModels<MainViewModel>()
    val detailsViewModel by viewModels<DetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidweatherappTheme {
                val mainUiState = mainViewModel.state.collectAsState()
                val detailsUiState = detailsViewModel.state.collectAsState()

                WeatherNavHost(
                    mainViewModel = mainViewModel,
                    detailsViewModel = detailsViewModel,
                    mainState = mainUiState.value,
                    detailsState = detailsUiState.value
                )
            }
        }
    }
}