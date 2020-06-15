package com.yeonproject.dodam_mvvm.view.my_word

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.data.room.entity.MyWordEntity
import com.yeonproject.dodam_mvvm.databinding.FragmentMyWordBinding
import com.yeonproject.dodam_mvvm.view.base.BaseFragment
import com.yeonproject.dodam_mvvm.view.my_word.adapter.MyWordAdapter
import com.yeonproject.dodam_mvvm.view.view_model.MyWordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyWordFragment : BaseFragment<FragmentMyWordBinding>(R.layout.fragment_my_word) {
    private val dispatcher by lazy {
        requireActivity().onBackPressedDispatcher
    }
    private lateinit var language: String
    private lateinit var listener: OnClickListener
    private var myWordAdapter = MyWordAdapter()
    private val viewModel by viewModel<MyWordViewModel>()

    interface OnClickListener {
        fun onClick(fragment: Fragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = (context as OnClickListener)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        language = arguments?.getString(LANGUAGE) ?: ""

        viewModel.getMyWordList()
        setupViewModel()

        binding.rvMyWordList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = myWordAdapter
        }

        binding.btnBack.setOnClickListener {
            dispatcher.onBackPressed()
        }

        myWordAdapter.setOnClickListener(object :
            MyWordAdapter.OnClickListener {
            override fun onClick(word: MyWordEntity) {
                if (language == HANGUL) {
                    listener.onClick(
                        MyWordDetailFragment.newInstance(
                            language,
                            word.hangul,
                            word.image
                        )
                    )
                } else if (language == ENGLISH) {
                    listener.onClick(
                        MyWordDetailFragment.newInstance(
                            language,
                            word.english,
                            word.image
                        )
                    )
                }

            }
        })
    }

    private fun setupViewModel() {
        viewModel.myWordList.observe(this, Observer {
            myWordAdapter.addData(it)
        })
    }

    companion object {
        private const val LANGUAGE = "language"
        private const val HANGUL = "hangul"
        private const val ENGLISH = "english"
        fun newInstance(language: String) = MyWordFragment().apply {
            arguments = Bundle().apply {
                putString(LANGUAGE, language)
            }
        }
    }
}