package com.yeonproject.dodam_mvvm.view.review

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.data.model.WordItem
import com.yeonproject.dodam_mvvm.databinding.FragmentReviewBinding
import com.yeonproject.dodam_mvvm.ext.glideImageSet
import com.yeonproject.dodam_mvvm.ext.shortToast
import com.yeonproject.dodam_mvvm.view.base.BaseFragment
import com.yeonproject.dodam_mvvm.view.view_model.WordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class ReviewFragment : BaseFragment<FragmentReviewBinding>(R.layout.fragment_review) {
    private lateinit var theme: String
    private var count: Int = 0
    private lateinit var language: String
    private val viewModel by viewModel<WordViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        theme = arguments?.getString(THEME) ?: ""
        language = arguments?.getString(LANGUAGE) ?: ""
        count = arguments?.getInt(COUNT) ?: 0

        viewModel.getWordList(theme, language)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.wordList.observe(viewLifecycleOwner, Observer {
            binding.layoutProgressBar.visibility = View.GONE
            binding.layoutReview.visibility = View.VISIBLE

            val answer = (activity as ReviewActivity).wordList[count].name
            val random = Random
            val reviewList = mutableListOf<WordItem>()
            val randomList = mutableListOf<WordItem>()
            var i = 1
            var k = 0
            for (i in it.indices) {
                if (it[i].name == answer) {
                    reviewList.add(it[i])
                }
            }

            while (i < 4) {
                val a = random.nextInt(10)
                reviewList.add(it[a])
                for (j in 0 until i) {
                    if (reviewList[i] == reviewList[j]) {
                        reviewList.removeAt(i)
                        i--
                    }
                }
                i++
            }

            binding.ivImage.glideImageSet(
                reviewList[0].image
            )

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

            binding.btnWord1.text = randomList[0].name
            binding.btnWord2.text = randomList[1].name
            binding.btnWord3.text = randomList[2].name
            binding.btnWord4.text = randomList[3].name

            binding.btnWord1.setOnClickListener {
                if (reviewList[0].name == binding.btnWord1.text) {
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
            binding.btnWord2.setOnClickListener {
                if (reviewList[0].name == binding.btnWord2.text) {
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
            binding.btnWord3.setOnClickListener {
                if (reviewList[0].name == binding.btnWord3.text) {
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
            binding.btnWord4.setOnClickListener {
                if (reviewList[0].name == binding.btnWord4.text) {
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
        })
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