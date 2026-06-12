package com.orbitalsonic.bottomnavsample.common.koin

import com.orbitalsonic.bottomnavsample.common.firebase.RemoteConfiguration
import com.orbitalsonic.bottomnavsample.common.network.InternetManager
import com.orbitalsonic.bottomnavsample.storage.preferences.SharedPrefManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DiComponent : KoinComponent {
    val internetManager by inject<InternetManager>()
    val sharedPrefManager by inject<SharedPrefManager>()

    // Remote Configuration
    val remoteConfiguration by inject<RemoteConfiguration>()
}