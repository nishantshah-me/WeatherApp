package com.weatherapp.presentation.forecast

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.weatherapp.core.presentation.Resource
import com.weatherapp.core.presentation.error
import com.weatherapp.core.presentation.loading
import com.weatherapp.core.presentation.success
import com.weatherapp.core.presentation.ui.BaseViewModel
import com.weatherapp.domain.usecase.ForecastUseCase
import com.weatherapp.presentation.model.*
import com.weatherapp.presentation.shared.FragmentPresentationModel
import io.reactivex.schedulers.Schedulers


class WeatherForecastViewModel(
    application: Application,
    private val forecastUseCase: ForecastUseCase
) : BaseViewModel(application) {


    private val _weatherForecast = MutableLiveData<Resource<WeatherForecastPresentation>>()
    val weatherForecast: LiveData<Resource<WeatherForecastPresentation>>
        get() = _weatherForecast


    private val _tabChangeListener = MutableLiveData<WeatherTab>()
    val tabChangeListener: LiveData<WeatherTab>
        get() = _tabChangeListener


    private val _dateChangeListener = MutableLiveData<WeatherDate>()
    val dateChangeListener: LiveData<WeatherDate>
        get() = _dateChangeListener


    init {
        _tabChangeListener.value = WeatherTab.DAY
    }

    fun getForecastData() = addCompositeDisposable(forecastUseCase.get()
        .doOnSubscribe { _weatherForecast.loading() }
        .subscribeOn(Schedulers.io())
        .map {
            it.mapToPresentationData()
        }
        .subscribe({
            _weatherForecast.success(it)
        }, {
            _weatherForecast.error(it.message)
        })
    )

    fun loadData(
        presentationData: WeatherForecastPresentation,
        tab: WeatherTab?
    ): FragmentPresentationModel {

        dateChangeListener.value?.let {
            return FragmentPresentationModel(
                now = loadNow(it.date, presentationData, tab),
                tom = loadTom(it.date, presentationData, tab)
            )
        } ?: run {
            return FragmentPresentationModel(null, null)
        }
    }

    private fun loadNow(
        date: String,
        presentationData: WeatherForecastPresentation,
        tab: WeatherTab?
    ): WeatherData? {
        var nowData: WeatherData? = null

        when (tab) {
            WeatherTab.DAY -> nowData = presentationData.days.firstOrNull {
                it.date == date
            }

            WeatherTab.NIGHT -> nowData = presentationData.nights.firstOrNull {
                it.date == date
            }
        }
        return nowData


    }

    private fun loadTom(
        date: String,
        presentationData: WeatherForecastPresentation,
        tab: WeatherTab?
    ): List<WeatherData>? {
        var tomData: List<WeatherData>? = null
        when (tab) {
            WeatherTab.DAY -> tomData = presentationData.days.filter {
                it.date != date
            }

            WeatherTab.NIGHT -> tomData = presentationData.nights.filter {
                it.date != date
            }
        }
        return tomData

    }

    fun filterData(position: Int?) {
        position?.let {
            _tabChangeListener.postValue(WeatherTab.getValue(position))
        }
    }

    fun dateChanged(weatherDate: WeatherDate) {
        _dateChangeListener.postValue(weatherDate)
    }

}

