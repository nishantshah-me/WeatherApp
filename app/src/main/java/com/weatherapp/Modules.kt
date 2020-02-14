package com.weatherapp

import com.weatherapp.data.datasource.IForecastRemoteDataSource
import com.weatherapp.data.repository.ForecastRepositoryImpl
import com.weatherapp.datasource.remote.ForecastRemoteDataSourceImpl
import com.weatherapp.datasource.remote.forecast.ForecastRequest
import com.weatherapp.domain.repository.IForecastRepository
import com.weatherapp.domain.usecase.ForecastUseCase
import com.weatherapp.presentation.city.CityViewModel
import com.weatherapp.presentation.forecast.WeatherForecastViewModel
import com.weatherapp.presentation.forecast.adapter.DatesAdapter
import com.weatherapp.presentation.localization.LocaleActivityImpl
import com.weatherapp.presentation.onboarding.SplashViewModel
import com.weatherapp.presentation.settings.SettingsViewModel
import com.weatherapp.presentation.shared.SharedViewModel
import com.weatherapp.presentation.today.adapter.ForecastAdapter
import com.weatherapp.presentation.today.adapter.WindsAdapter
import com.weatherapp.presentation.today.weather.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module


fun injectFeature() = loadFeature

/**
 * Top-level lazy property used to load koin modules.
 * Code inside the lazy block is executed only once, no matter how many times the property is accessed.
 * Can be used later through activity & fragment in dynamic feature approach for the feature & its respective modules.
 * */
private val loadFeature by lazy {

    loadKoinModules(
        listOf(
            viewModelModule,
            useCaseModule,
            repositoryModule,
            dataSourceModule,
            networkModule,
            adaptersModule,
            supportModule
        )
    )
}

private val viewModelModule: Module = module {
    viewModel { SplashViewModel(application = get()) }
    viewModel { WeatherForecastViewModel(application = get(), forecastUseCase = get()) }
    viewModel { SharedViewModel(application = get()) }
    viewModel { CityViewModel(application = get()) }
    viewModel { WeatherViewModel(application = get()) }
    viewModel { SettingsViewModel(application = get()) }

}

private val useCaseModule: Module = module {
    factory { ForecastUseCase(forecastRepository = get()) }
}

private val repositoryModule: Module = module {
    single { ForecastRepositoryImpl(remoteDataSource = get()) as IForecastRepository }
}

private val dataSourceModule: Module = module {
    single { ForecastRemoteDataSourceImpl(request = forecastsRequest) as IForecastRemoteDataSource }
}

private val networkModule: Module = module {
    single { forecastsRequest }
}

private val supportModule: Module = module {
    factory { LocaleActivityImpl() }
}

private val adaptersModule: Module = module {
    single { DatesAdapter() }
    single { ForecastAdapter() }
    single { WindsAdapter() }
}

private val forecastsRequest: ForecastRequest = ForecastRequest()


