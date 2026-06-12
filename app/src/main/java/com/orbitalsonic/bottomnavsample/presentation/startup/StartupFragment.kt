package com.orbitalsonic.bottomnavsample.presentation.startup

import com.orbitalsonic.bottomnavsample.R
import com.orbitalsonic.bottomnavsample.common.koin.DiComponent
import com.orbitalsonic.bottomnavsample.databinding.FragmentStartupBinding
import com.orbitalsonic.bottomnavsample.helpers.handlers.withDelay
import com.orbitalsonic.bottomnavsample.helpers.lifecycle.launchWhenResumed
import com.orbitalsonic.bottomnavsample.helpers.navigation.navigateTo
import com.orbitalsonic.bottomnavsample.presentation.base.fragments.BaseFragment
import org.koin.android.ext.android.getKoin
import org.koin.core.runOnKoinStarted

class StartupFragment :
    BaseFragment<FragmentStartupBinding>(FragmentStartupBinding::inflate) {

    private val diComponent by lazy { DiComponent() }

    override fun onViewCreated() {
        getKoin().runOnKoinStarted {
            getRemoteConfigValues()
            withDelay(2000) {
                moveDecision()
            }
        }

    }

    private fun getRemoteConfigValues() {
        diComponent.remoteConfiguration.checkRemoteConfig { fetchSuccessfully -> }
    }

    private fun moveDecision() {
        if (diComponent.sharedPrefManager.isFirstTimeEntrance) {
            firstTimeEnter()
        } else {
            secondTimeEnter()
        }
    }

    private fun firstTimeEnter() {
        launchWhenResumed {
            try {
                val action =
                    StartupFragmentDirections.actionStartupFragmentToStartupLanguageFragment()
                navigateTo(R.id.startupFragment, action)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    private fun secondTimeEnter() {
        launchWhenResumed {
            try {
                (activity as? StartupActivity)?.moveActivity()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}