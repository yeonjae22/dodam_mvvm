package com.yeonproject.dodam_mvvm.view.review

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.yeonproject.dodam_mvvm.Injection
import com.yeonproject.dodam_mvvm.data.model.WordItem
import com.yeonproject.dodam_mvvm.view.review.presenter.ReviewContract
import com.yeonproject.dodam_mvvm.view.review.presenter.ReviewPresenter
import com.yeonproject.dodam_mvvm.R
import kotlinx.android.synthetic.main.activity_review.*
import kotlin.random.Random

class ReviewActivity : AppCompatActivity(), ReviewContract.View {
    override lateinit var presenter: ReviewContract.Presenter
    private var themeName: String = ""
    private var language: String = ""
    val wordList = mutableListOf<WordItem>()

    override fun showWordList(items: List<WordItem>) {
        layout_progress_bar.visibility = View.GONE
        review_fragment.visibility = View.VISIBLE
        val random = Random
        var i = 0

        while (i < 10) {
            val a = random.nextInt(10)
            wordList.add(items[a])
            for (j in 0 until i) {
                if (wordList[i] == wordList[j]) {
                    wordList.removeAt(i)
                    i--
                }
            }
            i++
        }

        replace(ReviewFragment.newInstance(themeName, language, 0), false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        themeName = intent.getStringExtra(THEME)
        language = intent.getStringExtra(LANGUAGE)
        presenter = ReviewPresenter(
            Injection.wordRepository(), this
        )
        presenter.wordList(themeName, language)
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

    companion object {
        private const val THEME = "themeName"
        private const val LANGUAGE = "language"
    }
}
