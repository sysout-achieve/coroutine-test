package com.vulpeszerda.coroutinetest

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

/**
 * Nested launch operation order is not guaranteed
 */
fun launchTest1() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, throwable.message)
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        launch {
            Log.d(TAG, "nested launch")
        }
        Log.d(TAG, "parent launch")
    }
}

/**
 * Nested launch operation order is not guaranteed
 */
fun launchTest2() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, throwable.message)
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        launch {
            Log.d(TAG, "nested launch")
        }
        yield()
        Log.d(TAG, "parent launch")
    }
}

