package com.moh.dailyfresh

import android.app.Application
import com.moh.dailyfresh.utils.GlobalRuntimeVariableProvider

/**
 * Created by SObaidullah on 7/24/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
class DFApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        GlobalRuntimeVariableProvider.setContext(applicationContext)
    }
}