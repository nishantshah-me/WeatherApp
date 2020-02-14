package com.weatherapp.presentation.model

import com.weatherapp.forecast
import org.junit.Assert.assertTrue
import org.junit.Test

class WeatherForecastPresentationTest {


    @Test
    fun `map domain to presentation`() {
        // given
        val listOfForecast = listOf(forecast)

        // when
        val presentationItem = listOfForecast.mapToPresentationData()

        // then
        assertTrue(presentationItem.dates == listOfForecast.map { it.mapToWeatherDate() })
        assertTrue(presentationItem.days == listOfForecast.map { it.mapToDaysWeatherData() })
        assertTrue(presentationItem.nights == listOfForecast.map { it.mapToNightsWeatherData() })
        assertTrue(presentationItem.cities == listOfForecast.map { it.mapToPlacesAroundme() })

    }


}