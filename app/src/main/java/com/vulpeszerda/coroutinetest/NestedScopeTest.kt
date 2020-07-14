package com.vulpeszerda.coroutinetest

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Test for nested scope
 */
fun nestedScopeTest1() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope1 = CoroutineScope(SupervisorJob() + exceptionHandler)
    val scope2 = CoroutineScope(scope1.coroutineContext + SupervisorJob(scope1.coroutineContext[Job]))

    scope2.launch {
        delay(1000L)
        throw Exception("exception test")
    }

    scope2.launch {
        Log.d(TAG, "scope2 started")
        delay(2000L)
        Log.d(TAG, "scope2 finished")
    }
}
