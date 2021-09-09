package com.thetrusttech.getacarparts.ui.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import com.thetrusttech.getacarparts.models.response_maker.ApiResponse
import com.thetrusttech.getacarparts.models.responses.category.Recipe
import com.thetrusttech.getacarparts.repository.GCPRepository
import com.thetrusttech.getacarparts.ui.base.BaseViewModel

/**
 * Created by SObaidullah on 7/24/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
class MainViewModel(private val app: Application, private val repository: GCPRepository): BaseViewModel(app,repository) {

    fun getRecipeCategory(): LiveData<ApiResponse<Recipe>> {
        return repository.getContentCategory()
    }

}