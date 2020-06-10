package com.yeonproject.dodam_mvvm.view.word

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.data.model.WordItem
import com.yeonproject.dodam_mvvm.databinding.ActivityWordListBinding
import com.yeonproject.dodam_mvvm.view.base.BaseActivity
import com.yeonproject.dodam_mvvm.view.view_model.WordViewModel
import com.yeonproject.dodam_mvvm.view.word.adapter.WordAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordListActivity : BaseActivity<ActivityWordListBinding>(R.layout.activity_word_list) {
    private var wordAdapter = WordAdapter()
    private val viewModel by viewModel<WordViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val themeName = intent.getStringExtra(THEME)
        val language = intent.getStringExtra(LANGUAGE)
        viewModel.getWordList(themeName, language)
        setupViewModel()

        binding.rvWordList.apply {
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

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setupViewModel() {
        viewModel.wordList.observe(this, Observer {
            binding.layoutProgressBar.visibility = View.GONE
            binding.layoutWordList.visibility = View.VISIBLE
            wordAdapter.addData(it)
        })
    }

    companion object {
        private const val LANGUAGE = "language"
        private const val THEME = "themeName"
        private const val NAME = "name"
        private const val IMAGE = "image"
    }
}