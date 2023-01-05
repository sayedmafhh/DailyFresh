package com.thetrusttech.getacarparts.ui.items.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.thetrusttech.getacarparts.models.response_maker.ApiResponse
import com.thetrusttech.getacarparts.models.responses.details.Itemdetails
import com.thetrusttech.getacarparts.repository.GCPRepository
import com.thetrusttech.getacarparts.base.BaseViewModel

class ItemDetailsModel(private val app: Application, private val repository: GCPRepository): BaseViewModel(app,repository) {

    fun getDetails(id: String): LiveData<ApiResponse<Itemdetails>> {
        return repository.getDetails(id)
    }
}