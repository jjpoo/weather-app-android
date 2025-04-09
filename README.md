# Weather Application #
### A modern Android weather application built with Jetpack Compose that displays current weather information and forecasts using the [OpenWeatherMap API](https://openweathermap.org/). ###

## ***Features*** ##
1. Current weather information for predefined cities
2. Interactive city carousel with dynamic weather information
3. 5-day weather forecast with temperature trend graph
4. Toggle between Celsius and Fahrenheit temperature units


## ***Architecture*** ##

The application follows Clean Architecture principles with three main layers:

*Presentation Layer:*
Jetpack Compose UI components
ViewModels with StateFlow for UI state management
UI state classes for representing different screen states

*Domain Layer:*
Use cases for business logic
Repository interfaces
Domain models

*Data Layer:*
Repository implementations
Remote data source (OpenWeatherMap API)
Data models and mapping

## ***Technologies*** ##

Kotlin - Primary programming language
Jetpack Compose - Modern UI toolkit
Coroutines & Flow - Asynchronous programming
Hilt - Dependency injection
Retrofit - Network requests
Navigation Compose - In-app navigation
Material 3 - Design system

## ***Screens*** ##
### Main Screen ###

1. Carousel of predefined cities (Warsaw, Wroclaw, Krakow)
2. Current weather highlights (temperature, conditions, humidity)
3. Dynamic toolbar header that changes based on weather conditions
4. Details button to navigate to the forecast screen

### Details Screen ###

1. Temperature trend graph showing the next 5 days
2. Daily weather details in a scrollable list
3. Temperature unit toggle (Celsius/Fahrenheit)

### Error Screen ###

1. User-friendly error messages
2. Retry functionality
3. Navigation back to main screen

## ***Project Setup*** ##
### Prerequisites ###

1. Android Studio Flamingo or newer
2. JDK 17 or newer
3. Android SDK 34

### API Key ###
The app uses the OpenWeatherMap API. To run the app, you'll need to:

1. Create an account at OpenWeatherMap
2. Generate an API key
3. Add the key to your local.properties file:
4. WEATHER_API_KEY=your_api_key_here
