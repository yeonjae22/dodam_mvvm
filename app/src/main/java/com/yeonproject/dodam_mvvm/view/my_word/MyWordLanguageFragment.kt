package com.yeonproject.dodam_mvvm.view.my_word

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.databinding.FragmentLanguageBinding
import com.yeonproject.dodam_mvvm.view.base.BaseFragment

class MyWordLanguageFragment : BaseFragment<FragmentLanguageBinding>(R.layout.fragment_language) {
    private val dispatcher by lazy {
        requireActivity().onBackPressedDispatcher
    }
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

        binding.btnBack.setOnClickListener {
            dispatcher.onBackPressed()
        }

        binding.btnHangul.setOnClickListener {
            listener.onClick(MyWordFragment.newInstance(HANGUL))
        }

        binding.btnEnglish.setOnClickListener {
            listener.onClick(MyWordFragment.newInstance(ENGLISH))
        }
    }

    companion object {
        private const val HANGUL = "hangul"
        private const val ENGLISH = "english"
    }
}