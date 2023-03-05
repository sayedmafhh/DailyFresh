package com.thetrusttech.getacarparts.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.asgl.sdk.common.storeDataIntoDB
import com.thetrusttech.getacarparts.models.response_maker.ApiResponse
import com.thetrusttech.getacarparts.models.responses.category.Recipe
import com.thetrusttech.getacarparts.models.responses.details.Itemdetails
import com.thetrusttech.getacarparts.models.responses.items.Items
import com.thetrusttech.getacarparts.models.responses.make.CarMake
import com.thetrusttech.getacarparts.models.responses.model.CarModel
import com.thetrusttech.getacarparts.network.DFServiceProvider
import com.thetrusttech.getacarparts.utils.Constants.Companion.Pref_Key_Latitude
import com.thetrusttech.getacarparts.utils.Constants.Companion.Pref_Key_Location
import com.thetrusttech.getacarparts.utils.Constants.Companion.Pref_Key_Longitude
import com.thetrusttech.getacarparts.utils.GlobalRuntimeVariableProvider

/**
 * Created by SObaidullah on 7/24/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
class GCPRepository {
    val sharedPreference =  GlobalRuntimeVariableProvider
        .getContext()
        .getSharedPreferences("PrayerTime", Context.MODE_PRIVATE)

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

    fun getQuran() = DFServiceProvider.instance.getQuran()
    fun getZikr() = DFServiceProvider.instance.getZikr()
    fun getPrayerTime(url: String) = DFServiceProvider.instance.getPrayerTime(url)
    fun getPDF(url: String) = DFServiceProvider.instance.getPDF(url)

    fun storeLocationIntoPref(data: String) {
        storeDataIntoDB(sharedPreference, Pref_Key_Location, data)
    }
    fun storeLatitudeIntoPref(data: Double) {
        storeDataIntoDB(sharedPreference, Pref_Key_Location, data.toFloat())
    }
    fun storeLongitudeIntoPref(data: Double) {
        storeDataIntoDB(sharedPreference, Pref_Key_Location, data.toFloat())
    }
    fun getLocationFromPref(): String? {
        return sharedPreference.getString(Pref_Key_Location,"no-location")
    }
    fun getLatitudeFromPref(): Float {
        return sharedPreference.getFloat(Pref_Key_Latitude, 0f)
    }
    fun getLongitudeFromPref(): Float {
        return sharedPreference.getFloat(Pref_Key_Longitude, 0f)
    }

}