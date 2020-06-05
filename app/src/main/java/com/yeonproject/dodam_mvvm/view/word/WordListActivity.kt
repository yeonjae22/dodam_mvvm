package com.yeonproject.dodam_mvvm.view.word

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.yeonproject.dodam_mvvm.Injection
import com.yeonproject.dodam_mvvm.data.model.WordItem
import com.yeonproject.dodam_mvvm.view.word.adapter.WordAdapter
import com.yeonproject.dodam_mvvm.view.word.presenter.WordContract
import com.yeonproject.dodam_mvvm.view.word.presenter.WordPresenter
import com.yeonproject.dodam_mvvm.R
import kotlinx.android.synthetic.main.activity_word_list.*

class WordListActivity : AppCompatActivity(), WordContract.View {
    override lateinit var presenter: WordContract.Presenter
    private var wordAdapter = WordAdapter()

    override fun showWordList(items: List<WordItem>) {
        layout_progress_bar.visibility = View.GONE
        layout_word_list.visibility = View.VISIBLE
        wordAdapter.addData(items)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_list)
        presenter = WordPresenter(
            Injection.wordRepository(), this
        )
        val themeName = intent.getStringExtra(THEME)
        val language = intent.getStringExtra(LANGUAGE)
        presenter.getWordList(themeName, language)

        rv_word_list?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = wordAdapter
        }

        wordAdapter.setOnClickListener(object :
            WordAdapter.OnClickListener {
            override fun onClick(word: WordItem) {
                val intent = Intent(applicationContext, WordDetailActivity::class.java)
                intent.putExtra(NAME, word.name)
                intent.putExtra(IMAGE, word.image)
                intent.putExtra(LANGUAGE, language)
                startActivity(intent)
            }
        })

        btn_back.setOnClickListener {
            finish()
        }
    }

    companion object {
        private const val LANGUAGE = "language"
        private const val THEME = "themeName"
        private const val NAME = "name"
        private const val IMAGE = "image"
    }
}