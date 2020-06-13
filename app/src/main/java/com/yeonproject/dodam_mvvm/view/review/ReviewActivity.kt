package com.yeonproject.dodam_mvvm.view.review

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.data.model.WordItem
import com.yeonproject.dodam_mvvm.databinding.ActivityReviewBinding
import com.yeonproject.dodam_mvvm.view.base.BaseActivity
import com.yeonproject.dodam_mvvm.view.view_model.WordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class ReviewActivity : BaseActivity<ActivityReviewBinding>(R.layout.activity_review) {
    private var themeName: String = ""
    private var language: String = ""
    val wordList = mutableListOf<WordItem>()
    private val viewModel by viewModel<WordViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        themeName = intent.getStringExtra(THEME)
        language = intent.getStringExtra(LANGUAGE)
        viewModel.getWordList(themeName, language)
        setupViewModel()
    }

    fun replace(fragment: Fragment, isBackStack: Boolean = true) {
        if (isBackStack) {
            supportFragmentManager.beginTransaction().replace(R.id.review_fragment, fragment)
                .addToBackStack(null).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.review_fragment, fragment)
                .commit()

        }
    }

    private fun setupViewModel() {
        viewModel.wordList.observe(this, Observer {
            binding.layoutProgressBar.visibility = View.GONE
            binding.reviewFragment.visibility = View.VISIBLE
            val random = Random
            var i = 0

            while (i < 10) {
                val a = random.nextInt(10)
                wordList.add(it[a])
                for (j in 0 until i) {
                    if (wordList[i] == wordList[j]) {
                        wordList.removeAt(i)
                        i--
                    }
                }
                i++
            }

            replace(ReviewFragment.newInstance(themeName, language, 0), false)
        })
    }

    companion object {
        private const val THEME = "themeName"
        private const val LANGUAGE = "language"
    }
}
