package com.vulpeszerda.coroutinetest

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * CoroutineScope throw exception when it`s child job throws.
 */
fun launchExceptionOnCoroutineScopeTest1() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        coroutineScope {
            throw Exception("exception test")
        }
    }
}

/**
 * CoroutineScope throw exception when it`s child job throws.
 */
fun launchExceptionOnCoroutineScopeTest2() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        coroutineScope {
            launch {
                throw Exception("exception test")
            }
        }
    }
}

/**
 * even though wrap nested launch with try-catch,
 * exception propagates to nested launch`s parent job (coroutine scope).
 * Therefore coroutine scope will also throws.
 */
fun launchExceptionOnCoroutineScopeTest3() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        coroutineScope {
            try {
                launch {
                    throw Exception("exception test")
                }
            } catch (e: Exception) {
                Log.d(TAG, "catch exception: ${e.message}")
            }
        }
    }
}

/**
 * Unlike `launchExceptionOnCoroutineScopeTest3`,
 * because coroutineScope wrapped with try-catch,
 * exception will caught by try-catch.
 */
fun launchExceptionOnCoroutineScopeTest4() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        try {
            coroutineScope {
                launch {
                    throw Exception("exception test")
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "catch exception: ${e.message}")
        }
    }
}

