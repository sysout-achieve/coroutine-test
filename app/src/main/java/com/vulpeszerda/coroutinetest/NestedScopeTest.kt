package com.vulpeszerda.coroutinetest

import android.os.Handler
import android.os.Looper
import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

/**
 * Test for nested scope
 */
fun nestedScopeTest2() {
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
        Log.d(TAG, "scope2 job2 started")
        delay(2000L)
        Log.d(TAG, "scope2 job2 finished")
    }

    Handler(Looper.getMainLooper()).postDelayed({
        scope2.coroutineContext.cancelChildren()

        scope2.launch {
            Log.d(TAG, "scope2 job3 started")
            delay(1000L)
            Log.d(TAG, "scope2 job3 finished")
        }
    }, 500L)
}
