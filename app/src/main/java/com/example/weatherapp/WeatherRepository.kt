// WeatherRepository.kt
package com.example.weatherapp

import retrofit2.HttpException // Correct import statement

class WeatherRepository {
    companion object {
        suspend fun getWeatherData(date: String, year: String): WeatherData? {
            try {
                // Call the API to fetch weather data
                val response = RetrofitInstance.service.getHistoricalWeatherData(date, year)

                if (response.isSuccessful) {
                    // Parse the response body
                    val weatherResponse = response.body()

                    if (weatherResponse != null && weatherResponse.forecast.forecastDay.isNotEmpty()) {
                        // Fetch the first forecast day
                        val forecastDay = weatherResponse.forecast.forecastDay[0].day
                        val maxTemperature = forecastDay.maxtempC
                        val minTemperature = forecastDay.mintempC
                        return WeatherData(maxTemperature, minTemperature)
                    } else {
                        // Handle case when the response body is empty or forecast data is not available
                        throw IllegalStateException("No forecast data available")
                    }
                } else {
                    // Handle API error responses
                    throw HttpException(response)
                }
            } catch (e: HttpException) {
                // Handle HTTP-related errors (e.g., 404 Not Found)
                // You can access the error code and message using e.code() and e.message()
                e.printStackTrace()
                throw Exception("HTTP error: ${e.code()}")
            } catch (e: Exception) {
                // Handle other exceptions
                e.printStackTrace()
                throw Exception("An error occurred: ${e.message}")
            }
        }
    }
}


