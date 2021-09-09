package com.thetrusttech.getacarparts.network

import androidx.lifecycle.LiveData
import com.thetrusttech.getacarparts.models.response_maker.ApiResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

/**
 * Created by SObaidullah on 7/24/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */

internal const val UNKNOWN_CODE = -1

class LiveDataCallAdapter<R>(private val responseType: Type) : CallAdapter<R, LiveData<ApiResponse<R>>> {
    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): LiveData<ApiResponse<R>> {
        return object : LiveData<ApiResponse<R>>(){
            override fun onActive() {
                super.onActive()
                if (!call.isExecuted){
                    call.enqueue(object : Callback<R>{
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(ApiResponse.create(response))
                        }

                        override fun onFailure(call: Call<R>, t: Throwable) {
                            postValue(ApiResponse.create(UNKNOWN_CODE, t))
                        }

                    })
                } else {
                    dequeue()
                }
            }
            override fun onInactive() {
                super.onInactive()
                dequeue()
            }

            private fun dequeue() {
                if (call.isExecuted) call.cancel()
            }
        }
    }
}