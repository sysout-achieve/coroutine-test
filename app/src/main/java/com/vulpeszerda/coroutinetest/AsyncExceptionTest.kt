package com.vulpeszerda.coroutinetest

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

/**
 * Because async throws, parent job (launch) also throws.
 * `finished` will never called
 *
 * Even though you change parent scope`s `Job` as `SupervisorScope`,
 * this launch coroutine will always throws.
 */
fun asyncExceptionTest1() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        async {
            throw Exception("exception test")
        }
        yield()
        delay(1000L)
        Log.d(TAG, "finished")
    }
}

/**
 * CHECK RESULT!
 * Exception on this code will caught on both `UncaughtExceptionHandler` and our try-catch block.
 * And also `hahaha` printed
 */
fun asyncExceptionTest2() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        val job = async {
            throw Exception("exception test")
        }
        Log.d(TAG, "hahaha")
        try {
            job.await()
        } catch (e: Exception) {
            Log.d(TAG, "catch exception: ${e.message}")
        }
    }
}

/**
 * If we insert `yield` before `hahaha`, then exception only caught on `UncaughtExceptionHandler`
 * This always same even though you change `Job` as `SupervisorJob`
 */
fun asyncExceptionTest3() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        val job = async {
            throw Exception("exception test")
        }
        yield()
        Log.d(TAG, "hahaha")
        try {
            job.await()
        } catch (e: Exception) {
            Log.d(TAG, "catch exception: ${e.message}")
        }
    }
}

