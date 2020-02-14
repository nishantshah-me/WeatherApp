package com.weatherapp.datasource.remote.forecast

import com.weatherapp.core.network.BaseNetworkRequest
import com.weatherapp.datasource.entity.ForecastsEntity
import io.reactivex.Single


/**
 * A class used to make remote api requests
 *
 * */
class ForecastRequest : BaseNetworkRequest<ForecastApi>(ForecastApi::class.java) {


    fun getForecastData(): Single<ForecastsEntity> {
        return makeRequest().getForecasts()
    }

}