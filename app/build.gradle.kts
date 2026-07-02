plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.navigation.safe.args)
    alias(libs.plugins.ksp.plugin)
//    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.orbitalsonic.bottomnavsample"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.orbitalsonic.bottomnavsample"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    bundle {
        language {
            enableSplit = false
        }
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // WorkManager (prayer alarm reschedule)
    implementation(libs.work.runtime)

    // JSON (de)serialization library
    implementation(libs.gson)

    // Responsive layout sizes
    implementation(libs.sdp)
    implementation(libs.ssp)

    // Splash Screen API
    implementation(libs.splashscreen)

    // force update
    implementation(libs.app.update)
    // inApp Review
    implementation(libs.play.review)

    // Navigation components
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // ViewModel and LiveData
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.process)
    implementation(libs.lifecycle.service)

    // Firebase configuration with BoM
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.config)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.messaging.ktx)

    // Koin Dependency injection library
    implementation(libs.koin)
    implementation(libs.koin.core)

    // Image loading and caching library
    implementation(libs.glide)
    ksp(libs.glide.compiler)

    // Data store
    implementation(libs.androidx.datastore.preferences)

    // Lottie animation
    implementation(libs.lottie)
}