package com.moh.dailyfresh.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.moh.dailyfresh.R
import com.moh.dailyfresh.utils.GlobalRuntimeVariableProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * Created by bjayaram on 16,June,2021
 */

class OkHttpSingleton {

//Destroys the singleton instance of Http Client
    fun destroy() {
        ourInstance = null
    }


    companion object {
        private var ourInstance: OkHttpClient? = null
        private var timeOut: Long = 20.toLong()


        /**
         * This is a singleton instance of OkHttpClient. When you receive access token after login,
         * Call setAccessToken without failing for successful further transactions
         */
        val instance: OkHttpClient
            get() {
                if (ourInstance == null)
                    ourInstance = getHttpClient()
                return ourInstance!!
            }

        private fun getHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.HEADERS
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(interceptor)
                //added Chucker to track our API requests
                .addInterceptor(ChuckerInterceptor(GlobalRuntimeVariableProvider.getContext()!!))
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                //.addInterceptor( V4InterceptorSingleton.instance)
                //.authenticator(V4InterceptorSingleton.instance)
            return builder.build()
        }
    }
}