package com.moh.dailyfresh.network

import androidx.lifecycle.LiveData
import com.moh.dailyfresh.models.response_maker.ApiResponse
import com.moh.dailyfresh.models.responses.category.Recipe
import com.moh.dailyfresh.models.responses.items.Items
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by SObaidullah on 7/24/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
interface IAPIDF {

    @GET("api/v2/categories")
    fun getRecipeCategory(): LiveData<ApiResponse<Recipe>>

    @GET("api/v2/recipes")
    fun getRecipes(@Query("q") query: String, @Query("page") page:String ): LiveData<ApiResponse<Items>>

/*
    resourse : https://recipesapi.herokuapp.com/  ---- https://recipesapi.herokuapp.com/api/v2/recipes/36498
    helping reff : https://guides.codepath.com/android/consuming-apis-with-retrofit
    @GET("api/v2/recipes/{id}")
    fun getItemDetails(@Path("id") id: String): LiveData<ApiResponse<XYZ>>*/
}