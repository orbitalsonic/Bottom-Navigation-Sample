package com.orbitalsonic.bottomnavsample

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.orbitalsonic.bottomnavsample.common.koin.lazyModulesList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.lazyModules

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        setDarkLightMode()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainApplication)
            lazyModules(lazyModulesList)
        }
    }

    private fun setDarkLightMode() {
        val sharedPref = getSharedPreferences("app_preferences", MODE_PRIVATE)
        val themeMode =
            sharedPref.getInt("app_dark_light_mode", -1)  // "system", "light", or "dark"

        if (themeMode != AppCompatDelegate.getDefaultNightMode()) {
            when (themeMode) {
                1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }
}