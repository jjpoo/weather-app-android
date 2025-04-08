package com.polina.android.weather.app.utils

sealed class Result<T>(val data: T? = null, val error: String? = null) {
    class Success<T>(data: T?) : Result<T>(data)
    class Error<T>(data: T? = null, message: String) : Result<T>(error = message)
}