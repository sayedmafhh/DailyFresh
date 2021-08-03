package com.moh.dailyfresh.network

import retrofit2.converter.gson.GsonConverterFactory

object GsonConverterSingleton {
    val getGsonConverterFactoryInstance: GsonConverterFactory by lazy {
        return@lazy GsonConverterFactory.create()
    }
}