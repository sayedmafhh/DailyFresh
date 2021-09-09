package com.thetrusttech.getacarparts.network

/**
 * Created by SObaidullah on 7/24/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
class DFServiceProvider {

    companion object{
        var _instance: IAPIDF? = null

        val instance: IAPIDF
            get() {
                if (_instance == null)
                    _instance = getNetworkServices()
                return _instance!!
            }

        private fun getNetworkServices(): IAPIDF? {
            return DFRetrofitInstanceProvider.instanceLiveDataRetrofit?.create(IAPIDF::class.java)
        }
    }

    private fun destroy() {
        _instance = null
    }
}