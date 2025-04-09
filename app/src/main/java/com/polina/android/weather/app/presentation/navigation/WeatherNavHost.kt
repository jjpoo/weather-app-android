package com.polina.android.weather.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.polina.android.weather.app.presentation.details.composables.DetailsScreen
import com.polina.android.weather.app.presentation.details.state.DetailsViewModel
import com.polina.android.weather.app.presentation.details.state.WeatherDetailsUiState
import com.polina.android.weather.app.presentation.main.composables.MainScreen
import com.polina.android.weather.app.presentation.main.state.MainUiState
import com.polina.android.weather.app.presentation.main.state.MainViewModel
import com.polina.android.weather.app.utils.composables.ErrorScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun WeatherNavHost(
    mainViewModel: MainViewModel,
    detailsViewModel: DetailsViewModel,
    mainState: MainUiState,
    detailsState: WeatherDetailsUiState,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.MAIN_SCREEN
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Routes.MAIN_SCREEN) {
            MainScreen(
                state = mainState,
                onCitySelected = { city -> mainViewModel.selectCity(city) },
                onDetailsClick = {
                    navController.navigate(
                        Routes.createDetailsRoute(
                            (mainState as? MainUiState.Success)?.selectedCity?.name ?: ""
                        )
                    )
                },
                onRetry = { mainViewModel.retry() },
                onBackToMain = {
                    navController.navigate(Routes.MAIN_SCREEN) {
                        popUpTo(Routes.MAIN_SCREEN) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Routes.DETAILS_SCREEN,
            arguments = listOf(
                navArgument("cityName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""

            LaunchedEffect(cityName) {
                detailsViewModel.loadForecast(cityName)
            }

            DetailsScreen(
                cityName = cityName,
                state = detailsState,
                onToggleUnit = { detailsViewModel.toggleTemperatureUnit() },
                onBackPressed = { navController.popBackStack() },
                convertTemperature = { detailsViewModel.convertTemperature(it) },
                onRetry = { detailsViewModel.loadForecast(cityName) },
                onBackToMain = {
                    navController.navigate(Routes.MAIN_SCREEN) {
                        popUpTo(Routes.MAIN_SCREEN) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Routes.ERROR_SCREEN,
            arguments = listOf(
                navArgument("errorMessage") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val errorMessage = backStackEntry.arguments?.getString("errorMessage") ?: ""
            val decodedMessage = URLDecoder.decode(errorMessage, StandardCharsets.UTF_8.toString())

            ErrorScreen(
                errorMessage = decodedMessage,
                onRetry = { navController.popBackStack() },
                onBackToMain = {
                    navController.navigate(Routes.MAIN_SCREEN) {
                        popUpTo(Routes.MAIN_SCREEN) { inclusive = true }
                    }
                }
            )
        }
    }
}