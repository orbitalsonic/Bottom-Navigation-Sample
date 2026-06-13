package com.orbitalsonic.bottomnavsample.storage.preferences

import android.content.SharedPreferences

private const val FIRST_TIME_ENTRANCE_KEY = "first_time_entrance"
private const val APP_LANGUAGE_CODE_KEY = "app_language_code"
private const val DARK_MODE_KEY = "app_dark_light_mode"

class SharedPrefManager(private val sharedPreferences: SharedPreferences) {

    var isFirstTimeEntrance: Boolean
        get() = sharedPreferences.getBoolean(FIRST_TIME_ENTRANCE_KEY, true)
        set(value) {
            sharedPreferences.edit().apply {
                putBoolean(FIRST_TIME_ENTRANCE_KEY, value)
                apply()
            }
        }

    var appLanguageCode: String
        get() = sharedPreferences.getString(APP_LANGUAGE_CODE_KEY, "en") ?: "en"
        set(value) {
            sharedPreferences.edit().apply {
                putString(APP_LANGUAGE_CODE_KEY, value)
                apply()
            }
        }

    /**
     * 0 -> Dark
     * 1 -> Light
     * -1 -> System Default
     */
    var isAppDarkModeEnabled: Int
        get() = sharedPreferences.getInt(DARK_MODE_KEY, -1)
        set(value) {
            sharedPreferences.edit().apply {
                putInt(DARK_MODE_KEY, value)
                apply()
            }
        }

}