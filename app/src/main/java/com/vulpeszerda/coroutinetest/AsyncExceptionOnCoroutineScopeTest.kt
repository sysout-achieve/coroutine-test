package com.vulpeszerda.coroutinetest

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * CoroutineScope throw exception when it`s child job throws.
 */
fun asyncExceptionOnCoroutineScopeTest1() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        coroutineScope {
            async {
                throw Exception("exception test")
            }
        }
    }
}

/**
 * even though wrap nested async with try-catch,
 * exception propagates to nested launch`s parent job (coroutine scope).
 * Therefore coroutine scope will also throws.
 */
fun asyncExceptionOnCoroutineScopeTest2() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        coroutineScope {
            try {
                async {
                    throw Exception("exception test")
                }
            } catch (e: Exception) {
                Log.d(TAG, "catch exception: ${e.message}")
            }
        }
    }
}

/**
 * Unlike `asyncExceptionOnCoroutineScopeTest2`,
 * because coroutineScope wrapped with try-catch,
 * exception will caught by try-catch.
 */
fun asyncExceptionOnCoroutineScopeTest3() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        try {
            coroutineScope {
                async {
                    throw Exception("exception test")
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "catch exception: ${e.message}")
        }
    }
}
