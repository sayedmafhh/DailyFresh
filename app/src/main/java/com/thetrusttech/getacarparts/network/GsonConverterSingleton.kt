package com.thetrusttech.getacarparts.network

import retrofit2.converter.gson.GsonConverterFactory

object GsonConverterSingleton {
    val getGsonConverterFactoryInstance: GsonConverterFactory by lazy {
        return@lazy GsonConverterFactory.create()
    }
}