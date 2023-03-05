package com.thetrusttech.getacarparts.ui.home

import android.app.Application
import com.thetrusttech.getacarparts.base.BaseViewModel
import com.thetrusttech.getacarparts.repository.GCPRepository

class HomeFragViewModel (private val app: Application, private val repository: GCPRepository): BaseViewModel(app,repository) {

    fun getQuranData() = repository.getQuran()
}