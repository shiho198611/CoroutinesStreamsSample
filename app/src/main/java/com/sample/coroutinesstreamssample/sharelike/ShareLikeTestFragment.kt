package com.sample.coroutinesstreamssample.sharelike

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.coroutinesstreamssample.R
import kotlinx.android.synthetic.main.fragment_share_like.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ShareLikeTestFragment : Fragment(), View.OnClickListener {

    private lateinit var flowModel: ShareLikeFlowModel
    private lateinit var flowJob1: Job
    private lateinit var flowJob2: Job
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flowModel = ShareLikeFlowModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_share_like, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_cancel.setOnClickListener(this)

        flowJob1 = mainScope.launch {
            flowModel.getFlow()
                .asFlow()
                .onCompletion {
                    Log.d("flow_test", "complete")
                }.map {
                    "Flow message: $it"
                }
                .collect {
                    text_data1.text = it
                }
        }

        flowJob2 = mainScope.launch {
            flowModel.getFlow()
                .asFlow()
                .filter {
                    it % 2 == 0
                }
                .map {
                    "Add filter and map: $it"
                }
                .collect {
                    text_data2.text = it
                }
        }

    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_cancel) {
            flowJob1.cancel()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainScope.launch {
            flowJob1.cancelAndJoin()
            flowJob2.cancelAndJoin()
        }
    }
}