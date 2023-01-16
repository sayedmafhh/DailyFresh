package com.thetrusttech.getacarparts.ui.read_quran.model

import android.app.Application
import com.thetrusttech.getacarparts.base.BaseViewModel
import com.thetrusttech.getacarparts.repository.GCPRepository

class ReadQuranViewModel(private val app: Application, private val repository: GCPRepository): BaseViewModel(app,repository) {

    fun getQuranData() = repository.getQuran()

}