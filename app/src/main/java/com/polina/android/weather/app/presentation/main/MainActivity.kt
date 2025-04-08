package com.polina.android.weather.app.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.polina.android.weather.app.presentation.main.composables.MainScreen
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

                MainScreen(
                    state = state.value,
                    onCitySelected = { city ->
                        mainViewModel.selectCity(city)
                    },
                    onDetailsClick = {},
                    onBackToMain = {},
                    onRetry = {
                        mainViewModel.retry()
                    }
                )
            }
        }
    }
}
