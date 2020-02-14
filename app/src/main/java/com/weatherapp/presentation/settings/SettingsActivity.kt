package com.weatherapp.presentation.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.weatherapp.R
import com.weatherapp.core.presentation.ui.BaseActivity
import com.weatherapp.databinding.ActivitySettingsBinding
import com.weatherapp.presentation.localization.LocaleSelectorDialogFragment
import com.weatherapp.presentation.localization.SupportedLanguages
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class SettingsActivity : BaseActivity<ActivitySettingsBinding, SettingsViewModel>(),
    LocaleSelectorDialogFragment.OnItemSelectedListener {

    override fun onLocaleItemSelected(
        fragment: LocaleSelectorDialogFragment,
        item: Locale,
        index: Int
    ) {
        changeLocale(item)
    }

    private val activityModel: SettingsViewModel by viewModel()


    override fun getLayoutId(): Int {
        return R.layout.activity_settings
    }

    override fun getViewModel(): SettingsViewModel {
        return activityModel
    }


    override fun setUp(savedInstanceState: Bundle?) {
        getViewDataBinding().toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        getViewDataBinding().selectedLocale.text = Locale.getDefault().displayName
        getViewDataBinding().languageContainer.setOnClickListener {
            LocaleSelectorDialogFragment.newInstance()
                .show(supportFragmentManager, "LocaleSelectorDialogFragment")
        }
        SupportedLanguages.getList()
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }

}