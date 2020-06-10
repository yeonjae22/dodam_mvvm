package com.yeonproject.dodam_mvvm.view.intro

import android.content.Intent
import android.os.Bundle
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.databinding.FragmentThemeBinding
import com.yeonproject.dodam_mvvm.view.base.BaseFragment
import com.yeonproject.dodam_mvvm.view.review.ReviewActivity
import com.yeonproject.dodam_mvvm.view.word.WordListActivity

class ThemeFragment : BaseFragment<FragmentThemeBinding>(R.layout.fragment_theme) {
    private val dispatcher by lazy {
        requireActivity().onBackPressedDispatcher
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val index = arguments?.getInt(INDEX) ?: 0
        val language = arguments?.getString(LANGUAGE) ?: ""

        binding.btnBack.setOnClickListener {
            dispatcher.onBackPressed()
        }

        binding.btnFruit.setOnClickListener {
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

        binding.btnPlayground.setOnClickListener {

        }

        binding.btnZoo.setOnClickListener {
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

        binding.btnRestaurant.setOnClickListener {

        }

        binding.btnToy.setOnClickListener {

        }

        binding.btnHome.setOnClickListener {

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