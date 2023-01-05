package com.thetrusttech.getacarparts.base

import android.content.Context

class GlobalRuntimeVariableAccess {
    companion object {
        var context: Context? = null
            get() = field
            set(value) {
                field = value
            }
        var baseUrl: String? = null
            get() = field
            set(value) {
                field = value
            }
        var packageId: String? = null
            get() = field
            set(value) {
                field = value
            }


    }
}