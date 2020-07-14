package com.vulpeszerda.coroutinetest

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

/**
 * SupervisorScope will NOT throws even though it`s child job throws.
 */
fun asyncExceptionOnSupervisorScopeTest1() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        supervisorScope {
            async {
                throw Exception("exception test")
            }
        }
    }
}

/**
 * SupervisorScope will throws because scope itself throws
 */
fun asyncExceptionOnSupervisorScopeTest2() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        supervisorScope {
            val job = async {
                throw Exception("exception test")
            }
            job.await() // this throws exception
        }
    }
}

/**
 * If we wrap `await` with try-catch, then supervisorScope will not throws.
 */
fun asyncExceptionOnSupervisorScopeTest3() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "uncaught exception: ${throwable.message}")
    }
    val scope = CoroutineScope(Job() + exceptionHandler + Dispatchers.Main)
    scope.launch {
        supervisorScope {
            val job = async {
                throw Exception("exception test")
            }
            try {
                job.await() // this throws exception
            } catch (e: Exception) {
                Log.d(TAG, "catch exception : ${e.message}")
            }
        }
    }
}
