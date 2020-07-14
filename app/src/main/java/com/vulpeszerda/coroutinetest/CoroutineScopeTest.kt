package com.vulpeszerda.coroutinetest

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

/**
 * coroutineScope / supervisorScope guarantee operation order
 */
fun coroutineScopeTest1() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        coroutineScope {
            launch {
                Log.d(TAG, "nested launch")
            }
        }
        Log.d(TAG, "parent launch")
    }
}

/**
 * coroutineScope / supervisorScope guarantee operation order
 */
fun coroutineScopeTest2() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        coroutineScope {
            delay(1000L)
            launch {
                Log.d(TAG, "nested launch")
            }
        }
        yield()
        Log.d(TAG, "parent launch")
    }
}

