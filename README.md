# Weather App

## Overview
The **Weather App** is an Android application built using Jetpack Compose and Retrofit that fetches historical weather data from the OpenWeatherMap API. Users can input a date and year to retrieve the maximum and minimum temperatures for that day.

## Features
- User-friendly UI with Jetpack Compose
- Fetches historical weather data based on user input
- Displays maximum and minimum temperatures
- Error handling for network and API issues

## Tech Stack
- **Language:** Kotlin
- **Framework:** Jetpack Compose
- **Networking:** Retrofit
- **Dependency Injection:** Hilt (if needed in future updates)

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/NitishKumar1123/Weather-app.git
   cd Weather-app
   ```
2. Open the project in **Android Studio**
3. Sync Gradle dependencies
4. Run the application on an emulator or physical device

## API Configuration
This app uses OpenWeatherMap API. To use the app, replace the `API_KEY` in `WeatherApiService.kt` with your own API key:
```kotlin
companion object {
    const val API_KEY = "your_api_key_here"
}
```

## Project Structure
```
WeatherApp/
├── app/src/main/java/com/example/weatherapp/
│   ├── MainActivity.kt
│   ├── WeatherApp.kt
│   ├── WeatherRepository.kt
│   ├── WeatherData.kt
│   ├── WeatherResponse.kt
│   ├── WeatherApiService.kt
│   ├── RetrofitInstance.kt
└── AndroidManifest.xml
```

## Usage
1. Enter a **date (DD/MM)** and **year** in the provided text fields.
2. Click on the **Fetch Weather Data** button.
3. The app will display the **maximum** and **minimum temperatures** for the selected date.
4. If an error occurs (e.g., no data available, network issue), an error message will be displayed.

## Screenshots
(Include relevant screenshots here)

## License
This project is licensed under the **MIT License**. Feel free to modify and use it as needed.

## Author
Developed by **[Nitish Kumar](https://github.com/NitishKumar1123)**.

---
Feel free to contribute to this project by submitting issues and pull requests!

