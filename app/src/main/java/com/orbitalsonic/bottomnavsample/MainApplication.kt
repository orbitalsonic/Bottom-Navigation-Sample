package com.orbitalsonic.bottomnavsample

import android.app.Application
import com.orbitalsonic.bottomnavsample.common.koin.lazyModulesList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.lazyModules

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainApplication)
            lazyModules(lazyModulesList)
        }
    }
}