package com.weatherapp.datasource.remote

import com.weatherapp.data.datasource.IForecastRemoteDataSource
import com.weatherapp.datasource.entity.mapToDomain
import com.weatherapp.datasource.remote.forecast.ForecastRequest
import com.weatherapp.domain.model.Forecasts
import io.reactivex.Single


/**
 *
 * Forecast remote data source implementation.
 * */
class ForecastRemoteDataSourceImpl constructor(
    private val request: ForecastRequest
) : IForecastRemoteDataSource {

    /**
     *
     * Fetches latest forecast information from remote.
     * */
    override fun get(): Single<Forecasts> {
        return request.getForecastData().map { it.mapToDomain() }
    }


}