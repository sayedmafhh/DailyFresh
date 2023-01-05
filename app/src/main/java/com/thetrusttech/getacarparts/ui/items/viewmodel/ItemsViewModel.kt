package com.thetrusttech.getacarparts.ui.items.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.thetrusttech.getacarparts.models.response_maker.ApiResponse
import com.thetrusttech.getacarparts.models.responses.items.Items
import com.thetrusttech.getacarparts.repository.GCPRepository
import com.thetrusttech.getacarparts.base.BaseViewModel

class ItemsViewModel(private val app: Application, private val repository: GCPRepository): BaseViewModel(app,repository) {

    fun getRecipes(query: String, page: String): LiveData<ApiResponse<Items>> {
        return repository.getRecipes(query, page)
    }

}