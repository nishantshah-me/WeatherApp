package com.weatherapp.data.repository

import com.weatherapp.data.datasource.IForecastRemoteDataSource
import com.weatherapp.domain.model.Forecast
import com.weatherapp.domain.repository.IForecastRepository
import io.reactivex.Single


/**
 *
 * Forecast Repository implementaion from its data sources.
 * Currently handles only remote data source
 * */
class ForecastRepositoryImpl constructor(
    private val remoteDataSource: IForecastRemoteDataSource
) : IForecastRepository {


    /**
     * Fetches forecast information and maps it to domain entities.
     *
     * */
    override fun get(): Single<List<Forecast>> {
        return remoteDataSource.get().map { it.forecasts }
    }

}