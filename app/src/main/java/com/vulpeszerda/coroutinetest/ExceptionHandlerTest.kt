package com.vulpeszerda.coroutinetest

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

/**
 * Working.
 * Scope`s exceptionHandler
 */
fun exceptionHandlerTest1() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        throw Exception("exception test")
    }
}

/**
 * Working.
 * root coroutine exceptionHandler
 */
fun exceptionHandlerTest2() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + Dispatchers.Main)
    scope.launch(exceptionHandler) {
        throw Exception("exception test")
    }
}

/**
 * Not working.
 * nested exceptionHandler
 */
fun exceptionHandlerTest3() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + Dispatchers.Main)
    scope.launch {
        this.launch(exceptionHandler) {
            throw Exception("exception test")
        }
    }
}

/**
 * Working.
 * supervisorScope`s direct child exceptionHandler
 */
fun exceptionHandlerTest4() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job())
    scope.launch {
        supervisorScope {
            launch(exceptionHandler) {
                throw Exception("exception test")
            }
        }
    }
}

/**
 * Not working.
 * coroutineScope`s direct child exceptionHandler
 */
fun exceptionHandlerTest5() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job())
    scope.launch {
        coroutineScope {
            launch(exceptionHandler) {
                throw Exception("exception test")
            }
        }
    }
}
