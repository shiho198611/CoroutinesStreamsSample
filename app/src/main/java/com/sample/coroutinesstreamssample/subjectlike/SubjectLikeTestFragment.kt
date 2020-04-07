package com.sample.coroutinesstreamssample.subjectlike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.coroutinesstreamssample.R
import kotlinx.android.synthetic.main.fragment_normal_pattern.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class SubjectLikeTestFragment : Fragment(), View.OnClickListener, CoroutineScope by MainScope() {

    private val channel = BroadcastChannel<String>(1)
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

        btn_act.setOnClickListener(this)

        flowJob = launch {
            channel.asFlow()
                .flowOn(Dispatchers.IO)
                .collect { msg ->
                    text_stream_data.text = msg
                }
        }

    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_act) {
            launch {
                channel.send("msg: " + System.currentTimeMillis())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        launch {
            flowJob.cancelAndJoin()
        }
    }
}