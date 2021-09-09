package com.thetrusttech.getacarparts.ui.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.thetrusttech.getacarparts.repository.GCPRepository

/**
 * Created by SObaidullah on 7/15/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
open class BaseViewModel(
    private var app: Application,
    private var repository: GCPRepository? = null
    ): AndroidViewModel(app) {
    fun getBaseContext(): Context = app.baseContext
}