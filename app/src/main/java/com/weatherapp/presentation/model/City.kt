package com.weatherapp.presentation.model

import android.os.Parcelable
import com.weatherapp.domain.model.Forecast
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(

    val date: String,
    val name: String,
    val day: BaseWeather?,
    val night: BaseWeather?

) : Parcelable


fun Forecast.mapToCities(): List<City>? = night.cities?.let {

    it.map { nightPlace ->
        City(
            date = date,
            name = nightPlace?.name,
            day = day.cities?.first { nightPlace.name == it.name }?.weather?.mapToPresentation(date),
            night = nightPlace.weather.mapToPresentation(date)
        )

    }
}



