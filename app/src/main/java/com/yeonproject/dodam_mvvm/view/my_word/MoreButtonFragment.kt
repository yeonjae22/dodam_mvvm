package com.yeonproject.dodam_mvvm.view.my_word

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yeonproject.dodam_mvvm.R
import kotlinx.android.synthetic.main.fragment_more_button.*


class MoreButtonFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_more_button, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_modify.setOnClickListener {
            targetFragment?.onActivityResult(REQUEST_CODE, MODIFY_RESULT_CODE, Intent())
            dismiss()
        }
        btn_delete.setOnClickListener {
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