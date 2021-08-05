package com.moh.dailyfresh.repository

import androidx.lifecycle.LiveData
import com.moh.dailyfresh.models.response_maker.ApiResponse
import com.moh.dailyfresh.models.responses.category.Recipe
import com.moh.dailyfresh.models.responses.items.Items
import com.moh.dailyfresh.network.DFServiceProvider

/**
 * Created by SObaidullah on 7/24/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
class DFRepository {
    fun getContentCategory(): LiveData<ApiResponse<Recipe>> {
        return DFServiceProvider.instance.getRecipeCategory()
    }

    fun getRecipes(query: String, page: String): LiveData<ApiResponse<Items>> {
        return DFServiceProvider.instance.getRecipes(query, page)
    }
}