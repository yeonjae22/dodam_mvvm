package com.yeonproject.dodam_mvvm.view.my_word

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.databinding.FragmentMoreButtonBinding


class MoreButtonFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentMoreButtonBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_more_button, container, false)
        binding.lifecycleOwner = (this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnModify.setOnClickListener {
            targetFragment?.onActivityResult(REQUEST_CODE, MODIFY_RESULT_CODE, Intent())
            dismiss()
        }
        binding.btnDelete.setOnClickListener {
            targetFragment?.onActivityResult(REQUEST_CODE, DELETE_RESULT_CODE, Intent())
            dismiss()
        }
    }

    companion object {
        const val REQUEST_CODE = 0
        const val MODIFY_RESULT_CODE = 1
        const val DELETE_RESULT_CODE = 2
    }
}