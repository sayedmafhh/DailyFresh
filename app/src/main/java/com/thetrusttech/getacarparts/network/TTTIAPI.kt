package com.thetrusttech.getacarparts.network

import androidx.lifecycle.LiveData
import com.thetrusttech.getacarparts.models.response_maker.ApiResponse
import com.thetrusttech.getacarparts.models.responses.category.Recipe
import com.thetrusttech.getacarparts.models.responses.details.Itemdetails
import com.thetrusttech.getacarparts.models.responses.items.Items
import com.thetrusttech.getacarparts.models.responses.make.CarMake
import com.thetrusttech.getacarparts.models.responses.model.CarModel
import com.thetrusttech.getacarparts.ui.prayertime.PrayerTime
import com.thetrusttech.getacarparts.ui.prayertime.model.PrayerTimeResponse
import com.thetrusttech.getacarparts.ui.read_quran.model.Quran
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by SObaidullah on 7/24/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
interface TTTIAPI {

    @GET("api/v2/categories")
    fun getRecipeCategory(): LiveData<ApiResponse<Recipe>>

    @GET("api/v2/recipes")
    fun getRecipes(@Query("q") query: String, @Query("page") page:String ): LiveData<ApiResponse<Items>>

    @GET("api/v2/recipes/{id}")
    fun getItemDetails(@Path("id") id: String): LiveData<ApiResponse<Itemdetails>>

    @GET("api/make")
    fun getCarMake(): LiveData<ApiResponse<ArrayList<CarMake>>>

    @GET("api/model")
    fun getCarModel(): LiveData<ApiResponse<ArrayList<CarModel>>>

    @GET("api/v4/quran/verses/indopak")
    fun getQuran(): LiveData<ApiResponse<Quran>>

    @GET("api/v4/quran/verses/indopak")
    fun getQuranImages(): LiveData<ApiResponse<ResponseBody>>

    @GET("api/v4/quran/verses/indopak")
    fun getZikr(): LiveData<ApiResponse<Quran>>

    @GET()
    fun getPrayerTime(@Url url: String): LiveData<ApiResponse<PrayerTimeResponse>>

    @GET()
    fun getPDF(@Url url: String): LiveData<ApiResponse<ResponseBody>>
}