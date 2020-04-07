package com.sample.coroutinesstreamssample.sharelike

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.broadcastIn
import kotlinx.coroutines.flow.flow

class ShareLikeFlowModel {

    var cnt = 0
    private val channel: BroadcastChannel<Int>

    init {
        val mFlow = flow {
            while (cnt < 10000) {
                delay(1000)
                emit(cnt)
                cnt++
            }
        }

        channel = mFlow.broadcastIn(CoroutineScope(Dispatchers.IO))
    }

    fun getFlow(): BroadcastChannel<Int> {
        return channel
    }

}