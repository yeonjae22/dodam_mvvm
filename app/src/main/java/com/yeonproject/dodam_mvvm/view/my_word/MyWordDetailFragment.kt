package com.yeonproject.dodam_mvvm.view.my_word

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.databinding.FragmentMyWordDetailBinding
import com.yeonproject.dodam_mvvm.ext.glideImageSet
import com.yeonproject.dodam_mvvm.util.App
import com.yeonproject.dodam_mvvm.view.base.BaseFragment
import com.yeonproject.dodam_mvvm.view.word.WordPaint
import java.util.*

class MyWordDetailFragment :
    BaseFragment<FragmentMyWordDetailBinding>(R.layout.fragment_my_word_detail) {
    private val tts = TextToSpeech(App.instance.context(), TextToSpeech.OnInitListener {
        if (it != TextToSpeech.ERROR) {
            setting()
        }
    })

    private val dispatcher by lazy {
        requireActivity().onBackPressedDispatcher
    }
    private lateinit var listener: OnClickListener

    interface OnClickListener {
        fun onClick(fragment: Fragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = (context as OnClickListener)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var myPaint = WordPaint(context)
        binding.linear.addView(myPaint)

        binding.tvName.text = arguments?.getString(NAME)
        val image =
            context?.filesDir?.absoluteFile.toString() + "/" + arguments?.getString(IMAGE) ?: ""
        binding.ivImage.glideImageSet(
            image
        )

        binding.btnBack.setOnClickListener {
            dispatcher.onBackPressed()
        }

        binding.btnRed.setOnClickListener {
            myPaint.setColor(Color.RED)
        }

        binding.btnBlue.setOnClickListener {
            myPaint.setColor(Color.BLUE)
        }

        binding.btnBlack.setOnClickListener {
            myPaint.setColor(Color.BLACK)
        }

        binding.btnEraser.setOnClickListener {
            binding.linear.removeAllViews()
            myPaint = WordPaint(context)
            binding.linear.addView(myPaint)
        }

        binding.btnSound.setOnClickListener {
            tts.speak(binding.tvName.text.toString(), TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    private fun setting() {
        val language = arguments?.getString(LANGUAGE)
        if (language == "hangul") {
            tts.language = Locale.KOREA
        } else if (language == "english") {
            tts.language = Locale.ENGLISH
        }
        tts.setPitch(1.0f)
        tts.setSpeechRate(1.0f)
    }

    companion object {
        private const val LANGUAGE = "language"
        private const val NAME = "name"
        private const val IMAGE = "image"
        fun newInstance(language: String, name: String, image: String) =
            MyWordDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(LANGUAGE, language)
                    putString(NAME, name)
                    putString(IMAGE, image)
                }
            }
    }
}