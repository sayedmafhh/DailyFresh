package com.thetrusttech.getacarparts.ui.prayertime

import android.app.Application
import com.thetrusttech.getacarparts.base.BaseViewModel
import com.thetrusttech.getacarparts.repository.GCPRepository

class PrayerTimeViewModel(private val app: Application, private val repository: GCPRepository): BaseViewModel(app,repository) {

    fun getQuranData() = repository.getQuran()
}