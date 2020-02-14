package com.weatherapp.domain.repository

import com.weatherapp.domain.model.Forecast
import io.reactivex.Single


/**
 *
 * Forecast repository contract consumed by domain layer unaware of the its implementation
 * */
interface IForecastRepository {


    /**
     *
     * Fetches latest forecast information.
     * */
    fun get(): Single<List<Forecast>>

}