package com.thetrusttech.getacarparts.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object CoroutineUseCase {

    fun main(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }

    fun io(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }

    fun default(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Default).launch {
            work()
        }

    suspend fun withContextIo(work: suspend (() -> Boolean)): Boolean {
        return withContext(Dispatchers.IO) {
            work()
        }
    }
}