package com.thetrusttech.getacarparts.ui.prayertime

import android.app.Application
import com.thetrusttech.getacarparts.base.BaseViewModel
import com.thetrusttech.getacarparts.repository.GCPRepository

class PrayerTimeViewModel(private val app: Application, private val repository: GCPRepository): BaseViewModel(app,repository) {

    fun getPrayerTime(url: String) = repository.getPrayerTime(url)
    fun storeLocation(location: String) {
        repository.storeLocationIntoPref(location)
    }
    fun storeLatitude(latitude: Double) {
        repository.storeLatitudeIntoPref(latitude)
    }
    fun storeLongitude(longitude: Double) {
        repository.storeLongitudeIntoPref(longitude)
    }
    fun getLocation() = repository.getLocationFromPref()
    fun getLatitude() = repository.getLatitudeFromPref()
    fun getLongitude() = repository.getLongitudeFromPref()
}