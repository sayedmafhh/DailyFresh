package com.moh.dailyfresh.network

import androidx.lifecycle.LiveData
import com.moh.dailyfresh.models.response_maker.ApiResponse
import com.moh.dailyfresh.models.responses.Category
import com.moh.dailyfresh.models.responses.Recipe
import retrofit2.http.GET

/**
 * Created by SObaidullah on 7/24/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
interface IAPIDF {

    @GET("api/v2/categories")
    fun getRecipe(): LiveData<ApiResponse<Recipe>>
}