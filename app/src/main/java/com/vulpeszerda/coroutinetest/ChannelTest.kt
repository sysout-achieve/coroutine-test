package com.vulpeszerda.coroutinetest

import kotlinx.coroutines.channels.BroadcastChannel

fun channelTest1() {
    val channel = BroadcastChannel<Int>(1)
}
