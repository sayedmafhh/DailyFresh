package com.thetrusttech.getacarparts

import android.app.Application
import com.thetrusttech.getacarparts.utils.GlobalRuntimeVariableProvider

/**
 * Created by SObaidullah on 7/24/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
class TTApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        GlobalRuntimeVariableProvider.setContext(applicationContext)
    }
}