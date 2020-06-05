package com.yeonproject.dodam_mvvm.view.my_word

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yeonproject.dodam_mvvm.R
import kotlinx.android.synthetic.main.fragment_language.*

class MyWordLanguageFragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_language, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_back.setOnClickListener {
            dispatcher.onBackPressed()
        }

        btn_hangul.setOnClickListener {
            listener.onClick(MyWordFragment.newInstance(HANGUL))
        }

        btn_english.setOnClickListener {
            listener.onClick(MyWordFragment.newInstance(ENGLISH))
        }
    }

    companion object {
        private const val HANGUL = "hangul"
        private const val ENGLISH = "english"
    }
}