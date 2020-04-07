package com.sample.coroutinesstreamssample.errorresumelike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.coroutinesstreamssample.R
import kotlinx.android.synthetic.main.fragment_normal_pattern.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flowOn

class ErrorResumeLikeFragment : Fragment() {

    private val errorResumeLikeFlowModel = ErrorResumeLikeFlowDataModel()
    private val mainScope = MainScope()
    private lateinit var flowJob: Job

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_normal_pattern, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flowJob = mainScope.launch {
            errorResumeLikeFlowModel
                .getDataFlow()
                .flowOn(Dispatchers.IO)
                .catch {
                    emitAll(errorResumeLikeFlowModel.getNewDataFlow())
                }.collect {
                    text_stream_data.text = it
                }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainScope.launch {
            flowJob.cancelAndJoin()
        }
    }
}