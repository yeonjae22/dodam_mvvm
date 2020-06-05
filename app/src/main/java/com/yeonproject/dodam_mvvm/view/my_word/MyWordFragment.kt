package com.yeonproject.dodam_mvvm.view.my_word

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.yeonproject.dodam_mvvm.Injection
import com.yeonproject.dodam_mvvm.data.room.entity.MyWordEntity
import com.yeonproject.dodam_mvvm.view.my_word.adapter.MyWordAdapter
import com.yeonproject.dodam_mvvm.view.my_word.presenter.MyWordContract
import com.yeonproject.dodam_mvvm.view.my_word.presenter.MyWordPresenter
import com.yeonproject.dodam_mvvm.R
import kotlinx.android.synthetic.main.fragment_my_word.*

class MyWordFragment : Fragment(), MyWordContract.View {
    private val dispatcher by lazy {
        requireActivity().onBackPressedDispatcher
    }
    override lateinit var presenter: MyWordContract.Presenter
    private lateinit var language: String
    private lateinit var listener: OnClickListener
    private var myWordAdapter = MyWordAdapter()

    interface OnClickListener {
        fun onClick(fragment: Fragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = (context as OnClickListener)
    }

    override fun showMyWordList(response: List<MyWordEntity>) {
        myWordAdapter.addData(response)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_my_word, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        language = arguments?.getString(LANGUAGE) ?: ""
        presenter = MyWordPresenter(Injection.myWordRepository(), this)
        presenter.getMyWordList()
        rv_my_word_list?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = myWordAdapter
        }

        btn_back.setOnClickListener {
            dispatcher.onBackPressed()
        }

        myWordAdapter.setOnClickListener(object :
            MyWordAdapter.OnClickListener {
            override fun onClick(word: MyWordEntity) {
                if (language == HANGUL) {
                    listener.onClick(MyWordDetailFragment.newInstance(language, word.hangul, word.image))
                } else if (language == ENGLISH) {
                    listener.onClick(MyWordDetailFragment.newInstance(language, word.english, word.image))
                }

            }
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