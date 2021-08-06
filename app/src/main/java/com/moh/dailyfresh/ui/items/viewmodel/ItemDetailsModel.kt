package com.moh.dailyfresh.ui.items.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.moh.dailyfresh.models.response_maker.ApiResponse
import com.moh.dailyfresh.models.responses.details.Itemdetails
import com.moh.dailyfresh.repository.DFRepository
import com.moh.dailyfresh.ui.base.BaseViewModel

class ItemDetailsModel(private val app: Application, private val repository: DFRepository): BaseViewModel(app,repository) {


    fun getDetails(id: String): LiveData<ApiResponse<Itemdetails>> {
        return repository.getDetails(id)
    }
}