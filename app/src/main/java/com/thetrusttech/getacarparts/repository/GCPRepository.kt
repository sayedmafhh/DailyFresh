package com.thetrusttech.getacarparts.repository

import androidx.lifecycle.LiveData
import com.thetrusttech.getacarparts.models.response_maker.ApiResponse
import com.thetrusttech.getacarparts.models.responses.category.Recipe
import com.thetrusttech.getacarparts.models.responses.details.Itemdetails
import com.thetrusttech.getacarparts.models.responses.items.Items
import com.thetrusttech.getacarparts.models.responses.make.CarMake
import com.thetrusttech.getacarparts.models.responses.model.CarModel
import com.thetrusttech.getacarparts.network.DFServiceProvider

/**
 * Created by SObaidullah on 7/24/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
class GCPRepository {
    fun getContentCategory(): LiveData<ApiResponse<Recipe>> {
        return DFServiceProvider.instance.getRecipeCategory()
    }

    fun getRecipes(query: String, page: String): LiveData<ApiResponse<Items>> {
        return DFServiceProvider.instance.getRecipes(query, page)
    }

    fun getDetails(id: String): LiveData<ApiResponse<Itemdetails>> {
        return DFServiceProvider.instance.getItemDetails(id)
    }

    fun getCarMake(): LiveData<ApiResponse<ArrayList<CarMake>>> {
        return DFServiceProvider.instance.getCarMake()
    }

    fun getCarModel(): LiveData<ApiResponse<ArrayList<CarModel>>> {
        return DFServiceProvider.instance.getCarModel()
    }
}