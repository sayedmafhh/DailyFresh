package com.moh.dailyfresh.utils

import android.content.Context

/**
 * Created by SObaidullah on 7/24/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
class GlobalRuntimeVariableProvider {
    companion object {
        private var appContext: Context? = null

        fun setContext(aContext: Context) {
            appContext = aContext
        }

        fun getContext(): Context? {
            return appContext
        }
    }
}