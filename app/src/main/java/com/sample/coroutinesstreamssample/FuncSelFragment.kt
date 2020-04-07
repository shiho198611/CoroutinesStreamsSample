package com.sample.coroutinesstreamssample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_func_sel.*

class FuncSelFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_func_sel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_goto_share_like.setOnClickListener(this)
        btn_goto_merge_like.setOnClickListener(this)
        btn_goto_error_resume_like.setOnClickListener(this)
        btn_goto_subject_like.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_goto_error_resume_like -> {
                val action =
                    FuncSelFragmentDirections.actionFuncSelFragmentToErrorResumeLikeFragment()
                view.findNavController().navigate(action)
            }
            R.id.btn_goto_merge_like -> {
                val action = FuncSelFragmentDirections.actionFuncSelFragmentToMergeLikeFragment()
                view.findNavController().navigate(action)
            }
            R.id.btn_goto_share_like -> {
                val action =
                    FuncSelFragmentDirections.actionFuncSelFragmentToShareLikeTestFragment()
                view.findNavController().navigate(action)
            }
            R.id.btn_goto_subject_like -> {
                val action =
                    FuncSelFragmentDirections.actionFuncSelFragmentToSubjectLikeTestFragment()
                view.findNavController().navigate(action)
            }
        }

    }

}