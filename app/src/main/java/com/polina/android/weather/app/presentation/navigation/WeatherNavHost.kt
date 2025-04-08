package com.polina.android.weather.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.polina.android.weather.app.presentation.details.DetailsScreen
import com.polina.android.weather.app.presentation.main.MainUiState
import com.polina.android.weather.app.presentation.main.MainViewModel
import com.polina.android.weather.app.presentation.main.composables.MainScreen
import com.polina.android.weather.app.utils.composables.ErrorScreen
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun WeatherNavHost(
    viewModel: MainViewModel,
    state: MainUiState,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.MAIN_SCREEN
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Routes.MAIN_SCREEN) {
            MainScreen(
                state = state,
                onCitySelected = { city -> viewModel.selectCity(city) },
                onDetailsClick = {
                    navController.navigate(
                        Routes.createDetailsRoute(
                            (state as? MainUiState.Success)?.selectedCity?.name ?: ""
                        )
                    )
                },
                onRetry = { viewModel.retry() },
                onBackToMain = {
                    navController.navigate(Routes.MAIN_SCREEN) {
                        popUpTo(Routes.MAIN_SCREEN) { inclusive = true }
                    }
                },
                onError = { errorMessage ->
                    val encodedMessage =
                        URLEncoder.encode(errorMessage, StandardCharsets.UTF_8.toString())
                    navController.navigate(Routes.createErrorRoute(encodedMessage))
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
            DetailsScreen(

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