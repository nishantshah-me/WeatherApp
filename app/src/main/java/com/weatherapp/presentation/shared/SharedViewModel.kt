package com.weatherapp.presentation.shared

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.weatherapp.core.presentation.Resource
import com.weatherapp.core.presentation.ui.BaseViewModel
import com.weatherapp.presentation.model.WeatherData


/**
 * Model used to share data between the activity & fragments
 *
 * */
open class SharedViewModel(application: Application) : BaseViewModel(application) {

    val fragmentPresentationData = MutableLiveData<Resource<FragmentPresentationModel>>()
    val fragmentReloadData = MutableLiveData<Boolean>()

}

data class FragmentPresentationModel(
    val now: WeatherData?,
    val tom: List<WeatherData>?
)

