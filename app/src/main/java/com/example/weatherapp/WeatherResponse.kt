// WeatherResponse.kt
package com.example.weatherapp

import com.google.gson.annotations.SerializedName
//
//data class WeatherResponse(
//    @SerializedName("forecast") val forecast: ForecastData
//)
//
//data class ForecastData(
//    @SerializedName("forecastday") val forecastDay: List<ForecastDayData>
//)

//data class ForecastDayData(
//    @SerializedName("date") val date: String,
//    @SerializedName("day") val day: DayData
//)
//
//data class DayData(
//    @SerializedName("maxtemp_c") val maxtempC: String,
//    @SerializedName("mintemp_c") val mintempC: String
//)

// Modify WeatherResponse and other related classes based on the API response structure described in the documentation
data class WeatherResponse(
    @SerializedName("forecast") val forecast: ForecastData
)

data class ForecastData(
    @SerializedName("forecastday") val forecastDay: List<ForecastDayData>
)

data class ForecastDayData(
    @SerializedName("date") val date: String,
    @SerializedName("day") val day: DayData,
    @SerializedName("astro") val astro: AstroData // Add astro data
)

data class DayData(
    @SerializedName("maxtemp_c") val maxtempC: String,
    @SerializedName("mintemp_c") val mintempC: String
)

data class AstroData(
    @SerializedName("sunrise") val sunrise: String,
    @SerializedName("sunset") val sunset: String
)