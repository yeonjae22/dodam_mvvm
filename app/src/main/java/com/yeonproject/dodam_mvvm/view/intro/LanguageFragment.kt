package com.yeonproject.dodam_mvvm.view.intro

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.databinding.FragmentLanguageBinding
import com.yeonproject.dodam_mvvm.view.base.BaseFragment

class LanguageFragment : BaseFragment<FragmentLanguageBinding>(R.layout.fragment_language) {
    private val dispatcher by lazy {
        requireActivity().onBackPressedDispatcher
    }

    //    private val backPressedCallback = object : OnBackPressedCallback(true) {
//        override fun handleOnBackPressed() {
//
//        }
//    }
    private lateinit var listener: OnClickListener

    interface OnClickListener {
        fun onClick(fragment: Fragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = (context as OnClickListener)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //dispatcher.addCallback(this, backPressedCallback)

        val index = arguments?.getInt(INDEX) ?: 0

        binding.btnBack.setOnClickListener {
            dispatcher.onBackPressed()
        }

        binding.btnHangul.setOnClickListener {
            listener.onClick(ThemeFragment.newInstance(index, HANGUL))
        }

        binding.btnEnglish.setOnClickListener {
            listener.onClick(ThemeFragment.newInstance(index, ENGLISH))
        }
    }

    companion object {
        private const val INDEX = "index"
        private const val HANGUL = "hangul"
        private const val ENGLISH = "english"
        fun newInstance(index: Int) = LanguageFragment().apply {
            arguments = Bundle().apply {
                putInt(INDEX, index)
            }
        }
    }
}