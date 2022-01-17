package com.thetrusttech.getacarparts.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thetrusttech.getacarparts.repository.GCPRepository
import com.thetrusttech.getacarparts.ui.home.ui.home.HomeViewModel
import com.thetrusttech.getacarparts.ui.items.viewmodel.ItemDetailsModel
import com.thetrusttech.getacarparts.ui.items.viewmodel.ItemsViewModel
import com.thetrusttech.getacarparts.ui.items.viewmodel.MainViewModel

/**
 * Created by SObaidullah on 7/24/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
class ViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    private val repository: GCPRepository by lazy { GCPRepository() }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(app, repository) as T
            }
            modelClass.isAssignableFrom(ItemsViewModel::class.java) -> {
                ItemsViewModel(app, repository) as T
            }
            modelClass.isAssignableFrom(ItemDetailsModel::class.java) -> {
                ItemDetailsModel(app, repository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(app, repository) as T
            }

            else -> {
                throw IllegalArgumentException("${modelClass.simpleName} is an unknown type of ViewModel")
            }
        }
    }
}