package com.weatherapp.presentation.model

import com.weatherapp.domain.model.Forecast

data class WeatherDate(
    val date: String = "date"
)

fun Forecast.mapToWeatherDate(): WeatherDate = WeatherDate(date)