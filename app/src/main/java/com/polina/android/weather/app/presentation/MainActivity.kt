package com.polina.android.weather.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.polina.android.weather.app.presentation.main.MainViewModel
import com.polina.android.weather.app.presentation.navigation.WeatherNavHost
import com.polina.android.weather.app.presentation.theme.AndroidweatherappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidweatherappTheme {
                val state = mainViewModel.state.collectAsState()
                WeatherNavHost(
                    viewModel = mainViewModel,
                    state = state.value
                )
            }
        }
    }
}