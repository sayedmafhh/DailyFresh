package com.moh.dailyfresh.ui.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import com.moh.dailyfresh.models.response_maker.ApiResponse
import com.moh.dailyfresh.models.responses.Category
import com.moh.dailyfresh.models.responses.Recipe
import com.moh.dailyfresh.repository.DFRepository
import com.moh.dailyfresh.ui.base.BaseViewModel

/**
 * Created by SObaidullah on 7/24/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
class MainViewModel(private val app: Application, private val repository: DFRepository): BaseViewModel(app,repository) {

    fun getRecipe(): LiveData<ApiResponse<Recipe>> {
        return repository.getContent()
    }

}