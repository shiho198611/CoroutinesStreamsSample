package com.sample.coroutinesstreamssample.errorresumelike

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class ErrorResumeLikeFlowDataModel {

    private var cnt = 0

    fun getDataFlow(): Flow<String> {
        return flow {
            while (cnt < 1000) {
                delay(2000)
                if (cnt > 0 && cnt % 10 == 0) {
                    throw IOException()
                } else {
                    emit("msg: $cnt")
                }
                cnt++
            }
        }
    }

    fun getNewDataFlow(): Flow<String> {
        cnt = 0
        return flow {
            while (cnt < 1000) {
                delay(2000)
                emit("new msg: $cnt")
                cnt++
            }
        }
    }

}