package com.thetrusttech.getacarparts.ui.zikr

import android.app.Application
import com.thetrusttech.getacarparts.base.BaseViewModel
import com.thetrusttech.getacarparts.repository.GCPRepository

class ZikrViewModel (private val app: Application, private val repository: GCPRepository): BaseViewModel(app,repository) {
    fun getZikrData() = repository.getZikr()

}