package com.weatherapp.domain.usecase

import com.weatherapp.domain.model.Forecast
import com.weatherapp.domain.repository.IForecastRepository
import io.reactivex.Single


/**
 *
 * Fetches information from forecast repository to match further consumed by presentation layer.
 *
 * */
class ForecastUseCase constructor(private val forecastRepository: IForecastRepository) {


    fun get(): Single<List<Forecast>> =
        forecastRepository.get()

}






