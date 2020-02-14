package com.weatherapp.datasource.entity

import com.weatherapp.domain.model.Forecasts
import com.squareup.moshi.Json

/**
 *
 * Network response entity
 * */
data class ForecastsEntity(
    @field:Json(name = "forecasts") val forecasts: List<ForecastEntity>
)


fun ForecastsEntity.mapToDomain(): Forecasts =
    Forecasts(forecasts.mapToDomain())

fun List<ForecastsEntity>.mapToDomain(): List<Forecasts> = map { it.mapToDomain() }

