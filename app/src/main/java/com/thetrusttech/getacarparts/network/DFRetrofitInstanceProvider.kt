package com.thetrusttech.getacarparts.network

import com.thetrusttech.getacarparts.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit

/**
 * Created by SObaidullah on 7/24/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
class DFRetrofitInstanceProvider{
    companion object {
        var minstanceLiveDataRetrofit: Retrofit? = null
        val instanceLiveDataRetrofit: Retrofit?
            get() {
                if (minstanceLiveDataRetrofit == null)
                    return createRetrofitInstanceForLiveData(BASE_URL)
                return null
            }

        private fun createRetrofitInstanceForLiveData(baseUrl: String): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(LiveDataCallAdapterFactory())
                    .addConverterFactory(GsonConverterSingleton.getGsonConverterFactoryInstance)
                    .client(OkHttpSingleton.instance)
                    .build()
        }

    }
}