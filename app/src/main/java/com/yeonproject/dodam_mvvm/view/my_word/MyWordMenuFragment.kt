package com.yeonproject.dodam_mvvm.view.my_word

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.databinding.FragmentMyWordMenuBinding
import com.yeonproject.dodam_mvvm.view.base.BaseFragment

class MyWordMenuFragment : BaseFragment<FragmentMyWordMenuBinding>(R.layout.fragment_my_word_menu) {
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

        binding.btnRegister.setOnClickListener {
            listener.onClick(WordRegisterFragment())
        }

        binding.btnModify.setOnClickListener {
            listener.onClick(MyWordListFragment())
        }

        binding.btnLearning.setOnClickListener {
            listener.onClick(MyWordLanguageFragment())
        }
    }
}