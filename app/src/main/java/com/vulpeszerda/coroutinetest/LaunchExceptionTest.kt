package com.vulpeszerda.coroutinetest

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * exception on launch block will throw exception to `uncaughtExceptionHandler`
 */
fun launchExceptionTest1() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        throw Exception("exception test")
    }
}

/**
 * Because exception catched on launch block,
 * `uncaughtExceptionHandler` would not receive exception
 */
fun launchExceptionTest2() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        try {
            throw Exception("exception test")
        } catch (e: Exception) {
            Log.d(TAG, "catch exception: ${e.message}")
        }
    }
}
