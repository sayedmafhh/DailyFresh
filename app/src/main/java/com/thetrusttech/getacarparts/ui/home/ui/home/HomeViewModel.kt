package com.thetrusttech.getacarparts.ui.home.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thetrusttech.getacarparts.models.response_maker.ApiResponse
import com.thetrusttech.getacarparts.models.responses.category.Recipe
import com.thetrusttech.getacarparts.models.responses.make.CarMake
import com.thetrusttech.getacarparts.models.responses.model.CarModel
import com.thetrusttech.getacarparts.repository.GCPRepository
import com.thetrusttech.getacarparts.base.BaseViewModel

class HomeViewModel(private val app: Application, private val repository: GCPRepository): BaseViewModel(app,repository) {
//class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


    fun getCarMake(): LiveData<ApiResponse<ArrayList<CarMake>>> {
        return repository.getCarMake()
    }

    fun getCarModel(): LiveData<ApiResponse<ArrayList<CarModel>>> {
        return repository.getCarModel()
    }
/*
    fun getUserQuotation(): LiveData<ApiResponse<ArrayList<CarMake>>> {
        return repository.getCarMake()
    }

    fun getSellItem(): LiveData<ApiResponse<ArrayList<CarMake>>> {
        return repository.getCarMake()
    }*/
}