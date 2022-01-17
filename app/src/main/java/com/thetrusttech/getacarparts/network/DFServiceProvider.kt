package com.thetrusttech.getacarparts.network

/**
 * Created by SObaidullah on 7/24/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
class DFServiceProvider {

    companion object{
        var _instance: TTTIAPI? = null

        val instance: TTTIAPI
            get() {
                if (_instance == null)
                    _instance = getNetworkServices()
                return _instance!!
            }

        private fun getNetworkServices(): TTTIAPI? {
            return DFRetrofitInstanceProvider.instanceLiveDataRetrofit?.create(TTTIAPI::class.java)
        }
    }

    private fun destroy() {
        _instance = null
    }
}