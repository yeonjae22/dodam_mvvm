package com.yeonproject.dodam_mvvm.view.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.yeonproject.dodam_mvvm.data.model.WordItem
import com.yeonproject.dodam_mvvm.data.repository.word.WordRepositoryImpl
import com.yeonproject.dodam_mvvm.data.source.remote.word.WordRemoteDataSourceImpl
import com.yeonproject.dodam_mvvm.ext.shortToast
import com.yeonproject.dodam_mvvm.view.review.presenter.ReviewContract
import com.yeonproject.dodam_mvvm.view.review.presenter.ReviewPresenter
import com.yeonproject.dodam_mvvm.R
import kotlinx.android.synthetic.main.fragment_review.*
import kotlin.random.Random

class ReviewFragment : Fragment(), ReviewContract.View {
    private lateinit var theme: String
    private var count: Int = 0
    private lateinit var language: String
    override fun showWordList(items: List<WordItem>) {
        layout_progress_bar.visibility = View.GONE
        layout_review.visibility = View.VISIBLE

        val answer = (activity as ReviewActivity).wordList[count].name
        val random = Random
        val reviewList = mutableListOf<WordItem>()
        val randomList = mutableListOf<WordItem>()
        var i = 1
        var k = 0
        for (i in items.indices) {
            if (items[i].name == answer) {
                reviewList.add(items[i])
            }
        }

        while (i < 4) {
            val a = random.nextInt(10)
            reviewList.add(items[a])
            for (j in 0 until i) {
                if (reviewList[i] == reviewList[j]) {
                    reviewList.removeAt(i)
                    i--
                }
            }
            i++
        }
        Glide.with(this)
            .load(reviewList[0].image)
            .into(iv_image)

        while (k < 4) {
            val a = random.nextInt(4)
            randomList.add(reviewList[a])
            for (j in 0 until k) {
                if (randomList[k] == randomList[j]) {
                    randomList.removeAt(k)
                    k--
                }
            }
            k++
        }

        btn_word_1.text = randomList[0].name
        btn_word_2.text = randomList[1].name
        btn_word_3.text = randomList[2].name
        btn_word_4.text = randomList[3].name

        btn_word_1.setOnClickListener {
            if (reviewList[0].name == btn_word_1.text) {
                context?.shortToast(R.string.answer)
                count += 1
                if (count == 10) {
                    (activity as ReviewActivity).finish()
                } else {
                    (activity as ReviewActivity).replace(
                        newInstance(theme, language, count),
                        false
                    )
                }
            } else {
                context?.shortToast(R.string.wrong_answer)
            }
        }
        btn_word_2.setOnClickListener {
            if (reviewList[0].name == btn_word_2.text) {
                context?.shortToast(R.string.answer)
                count += 1
                if (count == 10) {
                    (activity as ReviewActivity).finish()
                } else {
                    (activity as ReviewActivity).replace(
                        newInstance(theme, language, count),
                        false
                    )
                }
            } else {
                context?.shortToast(R.string.wrong_answer)
            }
        }
        btn_word_3.setOnClickListener {
            if (reviewList[0].name == btn_word_3.text) {
                context?.shortToast(R.string.answer)
                count += 1
                if (count == 10) {
                    (activity as ReviewActivity).finish()
                } else {
                    (activity as ReviewActivity).replace(
                        newInstance(theme, language, count),
                        false
                    )
                }
            } else {
                context?.shortToast(R.string.wrong_answer)
            }
        }
        btn_word_4.setOnClickListener {
            if (reviewList[0].name == btn_word_4.text) {
                context?.shortToast(R.string.answer)
                count += 1
                if (count == 10) {
                    (activity as ReviewActivity).finish()
                } else {
                    (activity as ReviewActivity).replace(
                        newInstance(theme, language, count),
                        false
                    )
                }
            } else {
                context?.shortToast(R.string.wrong_answer)
            }
        }
    }

    override lateinit var presenter: ReviewContract.Presenter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_review, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        theme = arguments?.getString(THEME) ?: ""
        language = arguments?.getString(LANGUAGE) ?: ""
        count = arguments?.getInt(COUNT) ?: 0

        presenter = ReviewPresenter(
            WordRepositoryImpl.getInstance(
                WordRemoteDataSourceImpl.getInstance()
            ), this
        )
        presenter.wordList(theme, language)
    }

    companion object {
        private const val LANGUAGE = "language"
        private const val THEME = "themeName"
        private const val COUNT = "count"
        fun newInstance(theme: String, language: String, count: Int) = ReviewFragment().apply {
            arguments = Bundle().apply {
                putString(THEME, theme)
                putString(LANGUAGE, language)
                putInt(COUNT, count)
            }
        }
    }
}