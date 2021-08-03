package com.moh.dailyfresh.repository

import androidx.lifecycle.LiveData
import com.moh.dailyfresh.models.response_maker.ApiResponse
import com.moh.dailyfresh.models.responses.Category
import com.moh.dailyfresh.models.responses.Recipe
import com.moh.dailyfresh.network.DFServiceProvider

/**
 * Created by SObaidullah on 7/24/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
class DFRepository {
    fun getContent(): LiveData<ApiResponse<Recipe>> {
        return DFServiceProvider.instance.getRecipe()
    }
}