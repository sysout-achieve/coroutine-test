package com.vulpeszerda.coroutinetest

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.yield

/**
 * SupervisorScope throw exception when it`s child job throws.
 */
fun launchExceptionOnSupervisorScopeTest1() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        supervisorScope {
            throw Exception("exception test")
        }
    }
}

/**
 * SupervisorScope throw exception when it`s child job throws.
 */
fun launchExceptionOnSupervisorScopeTest2() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        supervisorScope {
            launch {
                throw Exception("exception test")
            }
        }
    }
}

/**
 * even though wrap nested launch with try-catch,
 * exception propagates to nested launch`s parent job (supervisor scope).
 * Therefore coroutine scope will also throws.
 */
fun launchExceptionOnSupervisorScopeTest3() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        supervisorScope {
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
 * Unlike `launchExceptionOnSupervisorScopeTest3`,
 * because coroutineScope wrapped with try-catch,
 * exception will caught by try-catch.
 */
fun launchExceptionOnSupervisorScopeTest4() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        try {
            supervisorScope {
                launch {
                    throw Exception("exception test")
                }
                // throw Exception("exception test")
            }
            yield()
            Log.d(TAG, "1111")
        } catch (e: Exception) {
            Log.d(TAG, "catch exception: ${e.message}")
        }
    }
}

