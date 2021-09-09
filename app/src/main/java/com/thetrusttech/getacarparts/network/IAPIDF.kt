package com.thetrusttech.getacarparts.network

import androidx.lifecycle.LiveData
import com.thetrusttech.getacarparts.models.response_maker.ApiResponse
import com.thetrusttech.getacarparts.models.responses.category.Recipe
import com.thetrusttech.getacarparts.models.responses.details.Itemdetails
import com.thetrusttech.getacarparts.models.responses.items.Items
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

    @GET("api/v2/recipes/{id}")
    fun getItemDetails(@Path("id") id: String): LiveData<ApiResponse<Itemdetails>>
}