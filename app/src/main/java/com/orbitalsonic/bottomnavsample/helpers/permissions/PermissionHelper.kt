package com.orbitalsonic.bottomnavsample.helpers.permissions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat


/*
fun Activity?.isLocationPermissionGranted(): Boolean {
    return this?.let {
        PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
            it,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) || PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
            it,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    } ?: false
}

fun Activity?.isGpsEnabled(): Boolean {
    return this?.let {
        val locationManager = it.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    } ?: false
}*/
