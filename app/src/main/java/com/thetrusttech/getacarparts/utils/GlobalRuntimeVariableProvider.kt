package com.thetrusttech.getacarparts.utils

import android.annotation.SuppressLint
import android.content.Context

class GlobalRuntimeVariableProvider {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null
        private var baseUrl: String? = null

        fun setContext(context: Context) {
            this.context = context
        }

        fun getContext(): Context {
            return context!!
        }

        fun getBaseUrl(): String{
            return baseUrl!!
        }
        fun setBaseUrl(baseUrl: String){
            this.baseUrl = baseUrl
        }
    }
}