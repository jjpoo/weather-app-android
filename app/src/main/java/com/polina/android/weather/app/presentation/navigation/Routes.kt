package com.polina.android.weather.app.presentation.navigation

object Routes {
    const val MAIN_SCREEN = "main_screen"
    const val DETAILS_SCREEN = "details_screen/{cityName}"
    const val ERROR_SCREEN = "error_screen/{errorMessage}"

    fun createDetailsRoute(cityName: String): String {
        return "details_screen/$cityName"
    }
}