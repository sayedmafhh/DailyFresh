package com.moh.dailyfresh.ui.base

import androidx.appcompat.app.AppCompatActivity

/**
 * Created by SObaidullah on 7/24/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
open class BaseActivity : AppCompatActivity() {

    val getViewModelFactory: ViewModelFactory? by lazy {
       application?.let {
            ViewModelFactory(it)
        }
    }
}