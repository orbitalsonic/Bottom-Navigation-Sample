package com.orbitalsonic.bottomnavsample.presentation.startup

import android.content.Intent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.orbitalsonic.bottomnavsample.R
import com.orbitalsonic.bottomnavsample.common.koin.DiComponent
import com.orbitalsonic.bottomnavsample.databinding.ActivityStartupBinding
import com.orbitalsonic.bottomnavsample.helpers.ui.goBackPressed
import com.orbitalsonic.bottomnavsample.presentation.MainActivity
import com.orbitalsonic.bottomnavsample.presentation.base.activities.BaseActivity
import org.koin.android.ext.android.getKoin
import org.koin.core.runOnKoinStarted

class StartupActivity : BaseActivity<ActivityStartupBinding>(ActivityStartupBinding::inflate) {

    private val diComponent by lazy { DiComponent() }
    private var navController: NavController? = null
    private var navListener: NavController.OnDestinationChangedListener? = null


    override fun initSplashScreen() {
        installSplashScreen()
    }

    override fun onCreated() {
        getKoin().runOnKoinStarted {
            initNavController()
            initNavListener()
        }
        goBackPressed {}
    }

    private fun initNavController() {
        navController =
            (supportFragmentManager.findFragmentById(binding.startupNavHostFragment.id) as NavHostFragment).navController
    }

    private fun initNavListener() {

        navListener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.startupFragment,
                R.id.startupLanguageFragment -> {
                }

                else -> {}
            }
        }

        navListener?.let {
            navController?.addOnDestinationChangedListener(it)
        }
    }

    private fun releaseResources() {
        navListener?.let {
            navController?.addOnDestinationChangedListener(it)
        }
    }

    fun moveActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        releaseResources()
        super.onDestroy()
    }
}