package com.moh.dailyfresh.ui.items.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.moh.dailyfresh.models.response_maker.ApiResponse
import com.moh.dailyfresh.models.responses.category.Recipe
import com.moh.dailyfresh.models.responses.items.Items
import com.moh.dailyfresh.repository.DFRepository
import com.moh.dailyfresh.ui.base.BaseViewModel

class ItemsViewModel(private val app: Application, private val repository: DFRepository): BaseViewModel(app,repository) {

    fun getRecipes(query: String, page: String): LiveData<ApiResponse<Items>> {
        return repository.getRecipes(query, page)
    }
}