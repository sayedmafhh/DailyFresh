package com.moh.dailyfresh.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moh.dailyfresh.repository.DFRepository
import com.moh.dailyfresh.ui.viewmodels.MainViewModel

/**
 * Created by SObaidullah on 7/24/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
class ViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    private val repository: DFRepository by lazy { DFRepository() }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(app, repository) as T
            }
            else -> {
                throw IllegalArgumentException("${modelClass.simpleName} is an unknown type of ViewModel")
            }
        }
    }
}