package com.weatherapp.presentation.model

import android.os.Parcelable
import com.weatherapp.domain.model.PhenomenonType
import com.weatherapp.domain.model.Weather
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BaseWeather(
    val date: String,
    val phenomenon: PhenomenonType,
    val tempmin: Double?,
    val tempmax: Double?
) : Parcelable


fun Weather.mapToPresentation(date: String) = BaseWeather(
    date = date,
    phenomenon = phenomenon,
    tempmax = tempmax,
    tempmin = tempmin
)

