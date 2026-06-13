package com.orbitalsonic.bottomnavsample.presentation.extras.settings

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.orbitalsonic.bottomnavsample.BuildConfig
import com.orbitalsonic.bottomnavsample.R
import com.orbitalsonic.bottomnavsample.common.koin.DiComponent
import com.orbitalsonic.bottomnavsample.databinding.FragmentSettingsBinding
import com.orbitalsonic.bottomnavsample.helpers.listener.RapidSafeListener.setOnRapidClickSafeListener
import com.orbitalsonic.bottomnavsample.helpers.navigation.navigateTo
import com.orbitalsonic.bottomnavsample.helpers.settings.bugReport
import com.orbitalsonic.bottomnavsample.helpers.settings.feedback
import com.orbitalsonic.bottomnavsample.helpers.settings.privacyPolicy
import com.orbitalsonic.bottomnavsample.helpers.settings.rateUs
import com.orbitalsonic.bottomnavsample.helpers.settings.shareApp
import com.orbitalsonic.bottomnavsample.helpers.settings.termsAndConditions
import com.orbitalsonic.bottomnavsample.presentation.base.fragments.BaseFragment

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    private val diComponent by lazy { DiComponent() }

    override fun onViewCreated() {
        initValues()
        setupClicks()
    }

    @SuppressLint("SetTextI18n")
    private fun initValues() {
        binding.mtvVersion.text = "V " + BuildConfig.VERSION_NAME
        updateDarkModeUi()
    }

    private fun setupClicks() {
        binding.apply {
            btnDarkMode.setOnRapidClickSafeListener { onDarkMode() }
            btnLanguage.setOnRapidClickSafeListener { onLanguage() }
            btnShare.setOnRapidClickSafeListener { onShareThisApp() }
            btnRate.setOnRapidClickSafeListener { onRateApp() }
            btnPrivacy.setOnRapidClickSafeListener { onPrivacyPolicy() }
            btnReportBug.setOnRapidClickSafeListener { onReportBug() }
            btnFeedback.setOnRapidClickSafeListener { onFeedback() }
            btnTerms.setOnRapidClickSafeListener { onTermsCondition() }
        }
    }

    private fun deviceInfo(): String {
        val stringBuilder = StringBuilder()

        stringBuilder.append("Please mention issue...: \n\n\n\n")

        // Device Info
        stringBuilder.append("Device Info \n")
        stringBuilder.append("Device: ${Build.DEVICE} \n")
        stringBuilder.append("Device Model: ${Build.MODEL} \n")
        stringBuilder.append("Device BRAND: ${Build.BRAND} \n")
        stringBuilder.append("Device MANUFACTURER: ${Build.MANUFACTURER} \n")
        stringBuilder.append("Version Name: ${BuildConfig.VERSION_NAME} \n")
        stringBuilder.append("Version Code: ${BuildConfig.VERSION_CODE} \n")


        return stringBuilder.toString()
    }

    private fun updateDarkModeUi() {
        val isDark = diComponent.sharedPrefManager.isAppDarkModeEnabled
        binding.topDarkModeIcon.setImageResource(if (isDark == 0) R.drawable.ic_st_mode_light else R.drawable.ic_st_mode_dark)
        binding.topDarkModeText.setText(if (isDark == 0) R.string.item_light_mode else R.string.item_dark_mode)
    }

    private fun onLanguage(){
        val action = SettingsFragmentDirections.actionSettingsFragmentToLanguageFragment()
        navigateTo(R.id.settingsFragment,action)
    }

    private fun onDarkMode() {
        diComponent.sharedPrefManager.apply {
            isAppDarkModeEnabled = if (isAppDarkModeEnabled == 0) 1 else 0

            if (isAppDarkModeEnabled == 0) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun onRateApp() {
        activity.rateUs()
    }

    private fun onShareThisApp() {
        activity.shareApp()
    }

    private fun onPrivacyPolicy() {
        activity.privacyPolicy()
    }

    private fun onTermsCondition() {
        activity.termsAndConditions()
    }

    private fun onReportBug() {
        activity.bugReport(deviceInfo())
    }

    private fun onFeedback() {
        activity.feedback()
    }
}