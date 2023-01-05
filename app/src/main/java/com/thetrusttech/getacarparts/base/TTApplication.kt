package com.thetrusttech.getacarparts.base

import android.app.Application
import com.thetrusttech.getacarparts.BuildConfig

class TTApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        GlobalRuntimeVariableAccess.packageId = BuildConfig.APPLICATION_ID
        GlobalRuntimeVariableAccess.context = applicationContext
        GlobalRuntimeVariableAccess.baseUrl = ""
    }
}