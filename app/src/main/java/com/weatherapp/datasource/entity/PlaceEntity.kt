package com.weatherapp.datasource.entity

import com.weatherapp.domain.model.PhenomenonType
import com.weatherapp.domain.model.Place
import com.weatherapp.domain.model.Weather
import com.squareup.moshi.Json


/**
 *
 * Network response entity
 * */
data class PlaceEntity(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "phenomenon") val phenomenon: String,
    @field:Json(name = "tempmin") val tempmin: Double?,
    @field:Json(name = "tempmax") val tempmax: Double?
)


fun PlaceEntity.mapToDomain(): Place =
    Place(name, Weather(PhenomenonType.getValue(phenomenon), tempmin, tempmax))

fun List<PlaceEntity>.mapToDomain(): List<Place> = map { it.mapToDomain() }

