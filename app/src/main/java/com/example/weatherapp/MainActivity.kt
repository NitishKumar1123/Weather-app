//// MainActivity.kt
//package com.example.weatherapp
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.KeyboardActions
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.Button
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.focus.FocusRequester
//import androidx.compose.ui.focus.focusRequester
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.unit.dp
//import com.example.weatherapp.ui.theme.WeatherAppTheme
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.launch
//import androidx.compose.ui.tooling.preview.Preview
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            WeatherApp()
//        }
//    }
//}
////
////@Composable
////fun WeatherApp() {
////    var date by remember { mutableStateOf("") }
////    var year by remember { mutableStateOf("") }
////    var maxTemperature by remember { mutableStateOf("") }
////    var minTemperature by remember { mutableStateOf("") }
////    var error by remember { mutableStateOf("") }
////
////    Column(
////        modifier = Modifier
////            .fillMaxSize()
////            .padding(16.dp),
////        verticalArrangement = Arrangement.spacedBy(16.dp)
////    ) {
////        OutlinedTextField(
////            value = date,
////            onValueChange = { date = it },
////            label = { Text("Date (DD/MM)") },
////            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
////            keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
////        )
////
////        OutlinedTextField(
////            value = year,
////            onValueChange = { year = it },
////            label = { Text("Year") },
////            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
////            keyboardActions = KeyboardActions(onDone = { /* Handle done action */ })
////        )
////
////        val scope = rememberCoroutineScope()
////        LaunchedEffect(Unit) {
////            scope.launch {
////                // Call API to fetch weather data
////                val weatherData = WeatherRepository.getWeatherData(date, year)
////
////                if (weatherData != null) {
////                    // Update UI with weather data
////                    maxTemperature = weatherData.maxTemperature ?: "N/A"
////                    minTemperature = weatherData.minTemperature ?: "N/A"
////                    error = ""
////                } else {
////                    // Display error message
////                    error = "Failed to fetch weather data"
////                }
////            }
////        }
////
////        if (error.isNotEmpty()) {
////            Text(
////                text = error,
////                modifier = Modifier.align(Alignment.CenterHorizontally)
////            )
////        } else {
////            Text(
////                text = "Max Temperature: $maxTemperature°C",
////                modifier = Modifier.align(Alignment.CenterHorizontally)
////            )
////
////            Text(
////                text = "Min Temperature: $minTemperature°C",
////                modifier = Modifier.align(Alignment.CenterHorizontally)
////            )
////        }
////    }
////}
////@Preview
////@Composable
////fun WeatherAppPreview() {
////    WeatherApp()
////}
package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherApp()
        }
    }
}

@Composable
fun WeatherApp() {
    var date by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var maxTemperature by remember { mutableStateOf("") }
    var minTemperature by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Date (DD/MM)") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { /* Handle next action */ })
        )

        OutlinedTextField(
            value = year,
            onValueChange = { year = it },
            label = { Text("Year") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { /* Handle done action */ })
        )

        val scope = rememberCoroutineScope()
        Button(
            onClick = {
                scope.launch {
                    try {
                        fetchWeatherData(date, year, { fetchedMax, fetchedMin ->
                            maxTemperature = fetchedMax
                            minTemperature = fetchedMin
                            error = ""
                        }) { errorMessage ->
                            error = errorMessage
                        }
                    } catch (e: Exception) {
                        error = "An error occurred: ${e.message}"
                    }
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Fetch Weather Data")
        }


        if (error.isNotEmpty()) {
            Text(
                text = error,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            Text(
                text = "Max Temperature: $maxTemperature°C",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(
                text = "Min Temperature: $minTemperature°C",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

private suspend fun fetchWeatherData(
    date: String,
    year: String,
    onSuccess: (String, String) -> Unit,
    onError: (String) -> Unit
) {
    try {
        // Call API to fetch weather data
        val weatherData = WeatherRepository.getWeatherData(date, year)

        if (weatherData != null) {
            // Update UI with weather data
            onSuccess(weatherData.maxTemperature ?: "N/A", weatherData.minTemperature ?: "N/A")
        } else {
            // Display error message
            onError("Failed to fetch weather data")
        }
    } catch (e: retrofit2.HttpException) {
        // Handle HTTP exceptions
        onError("HTTP error: ${e.code()}")
    } catch (e: IOException) {
        // Handle IO exceptions
        onError("Network error: ${e.message}")
    } catch (e: Exception) {
        // Handle other exceptions
        onError("An error occurred: ${e.message}")
    }
}


