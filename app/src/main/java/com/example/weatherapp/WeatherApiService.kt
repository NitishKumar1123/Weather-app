//// WeatherApiService.kt
package com.example.weatherapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://openweathermap.org/"

interface WeatherApiService {
    @GET("history.json")
    suspend fun getHistoricalWeatherData(
        @Query("date") date: String,
        @Query("year") year: String,
        @Query("key") apiKey: String = API_KEY
    ): Response<WeatherResponse>

    companion object {
        const val API_KEY = "965c14f6f9f3445b8024c27313466bb8"
    }
}




