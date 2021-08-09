package com.moh.dailyfresh.ui.base

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.moh.dailyfresh.repository.DFRepository

/**
 * Created by SObaidullah on 7/15/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
open class BaseViewModel(
    private var app: Application,
    private var dfRepository: DFRepository? = null
    ): AndroidViewModel(app) {
    fun getBaseContext(): Context = app.baseContext
}