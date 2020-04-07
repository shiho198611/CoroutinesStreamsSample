package com.sample.coroutinesstreamssample.mergelike

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MergeLikeFlowDataModel {

    var count = 0

    fun getDataFlow(): Flow<String> {
        return flow {
            while (count < 1000) {
                delay(2000)
                count ++
                emit("message count: $count")
            }
        }
    }

}