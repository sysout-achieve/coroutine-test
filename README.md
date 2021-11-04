# Coroutine test case for study

## [LaunchTest.kt](https://github.com/vulpeszerda/coroutine-test/blob/master/app/src/main/java/com/vulpeszerda/coroutinetest/LaunchTest.kt)
`Launch` operation execution order test.


## [CoroutineScopeTest.kt](https://github.com/vulpeszerda/coroutine-test/blob/master/app/src/main/java/com/vulpeszerda/coroutinetest/CoroutineScopeTest.kt)
`coroutineScope` and `supervisorScope` operation order test.

## [NestedScopeTest.kt](https://github.com/vulpeszerda/coroutine-test/blob/master/app/src/main/java/com/vulpeszerda/coroutinetest/NestedScopeTest.kt)
Multiple `CoroutineScope` execution test.

## [ExceptionHandlerTest.kt](https://github.com/vulpeszerda/coroutine-test/blob/master/app/src/main/java/com/vulpeszerda/coroutinetest/ExceptionHandlerTest.kt)
Global coroutine `ExceptionHandler` test.
Tests for when it will be called and when it will not be called.

## [LaunchExceptionTest.kt](https://github.com/vulpeszerda/coroutine-test/blob/master/app/src/main/java/com/vulpeszerda/coroutinetest/LaunchExceptionTest.kt)
Tests about how to handle exceptions on coroutine started with `launch`. 

## [AsyncExceptionTest.kt](https://github.com/vulpeszerda/coroutine-test/blob/master/app/src/main/java/com/vulpeszerda/coroutinetest/AsyncExceptionTest.kt)
Tests about how to handle exceptions on coroutine started with `async`. 

## [LaunchExceptionOnCoroutineScopeTest.kt](https://github.com/vulpeszerda/coroutine-test/blob/master/app/src/main/java/com/vulpeszerda/coroutinetest/LaunchExceptionOnCoroutineScopeTest.kt)
Tests about how to handle exceptions on coroutine that started with `launch` in common `Job` coroutine scope. 

## [LaunchExceptionOnSupervisorScopeTest.kt](https://github.com/vulpeszerda/coroutine-test/blob/master/app/src/main/java/com/vulpeszerda/coroutinetest/LaunchExceptionOnSupervisorScopeTest.kt)
Tests about how to handle exceptions on coroutine that started with `launch` in `SupervisorJob` coroutine scope. 

## [AsyncExceptionOnCoroutineScopeTest.kt](https://github.com/vulpeszerda/coroutine-test/blob/master/app/src/main/java/com/vulpeszerda/coroutinetest/AsyncExceptionOnCoroutineScopeTest.kt)
Tests about how to handle exceptions on coroutine that started with `async` in common `Job` coroutine scope. 


## [AsyncExceptionOnSupervisorScopeTest.kt](https://github.com/vulpeszerda/coroutine-test/blob/master/app/src/main/java/com/vulpeszerda/coroutinetest/AsyncExceptionOnSupervisorScopeTest.kt)
Tests about how to handle exceptions on coroutine that started with `async` in `SupervisorJob` coroutine scope. 

## [ChannelTest.kt](https://github.com/vulpeszerda/coroutine-test/blob/master/app/src/main/java/com/vulpeszerda/coroutinetest/ChannelTest.kt)
Tests for channel. TODO
