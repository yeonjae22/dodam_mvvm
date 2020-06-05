package com.yeonproject.dodam_mvvm.view.intro

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yeonproject.dodam_mvvm.view.review.ReviewActivity
import com.yeonproject.dodam_mvvm.view.word.WordListActivity
import com.yeonproject.dodam_mvvm.R
import kotlinx.android.synthetic.main.fragment_theme.*

class ThemeFragment : Fragment() {
    private val dispatcher by lazy {
        requireActivity().onBackPressedDispatcher
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_theme, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val index = arguments?.getInt(INDEX) ?: 0
        val language = arguments?.getString(LANGUAGE) ?: ""

        btn_back.setOnClickListener {
            dispatcher.onBackPressed()
        }

        btn_fruit.setOnClickListener {
            when (index) {
                1 -> {
                    val intent = Intent(context, WordListActivity::class.java)
                    intent.putExtra(LANGUAGE, language)
                    intent.putExtra(THEME_NAME, "fruit")
                    startActivity(intent)
                }
                2 -> {
                    val intent = Intent(context, ReviewActivity::class.java)
                    intent.putExtra(LANGUAGE, language)
                    intent.putExtra(THEME_NAME, "fruit")
                    startActivity(intent)
                }
            }
        }

        btn_playground.setOnClickListener {

        }

        btn_zoo.setOnClickListener {
            when (index) {
                1 -> {
                    val intent = Intent(context, WordListActivity::class.java)
                    intent.putExtra(LANGUAGE, language)
                    intent.putExtra(THEME_NAME, "zoo")
                    startActivity(intent)
                }
                2 -> {
                    val intent = Intent(context, ReviewActivity::class.java)
                    intent.putExtra(LANGUAGE, language)
                    intent.putExtra(THEME_NAME, "zoo")
                    startActivity(intent)
                }
            }
        }

        btn_restaurant.setOnClickListener {

        }

        btn_toy.setOnClickListener {

        }

        btn_home.setOnClickListener {

        }
    }

    companion object {
        private const val INDEX = "index"
        private const val LANGUAGE = "language"
        private const val THEME_NAME = "themeName"
        fun newInstance(index: Int, language: String) = ThemeFragment().apply {
            arguments = Bundle().apply {
                putInt(INDEX, index)
                putString(LANGUAGE, language)
            }
        }
    }
}