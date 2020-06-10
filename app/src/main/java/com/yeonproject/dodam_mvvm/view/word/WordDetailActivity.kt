package com.yeonproject.dodam_mvvm.view.word

import android.graphics.Color
import android.os.Bundle
import android.speech.tts.TextToSpeech
import com.yeonproject.dodam_mvvm.util.App
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.databinding.ActivityWordDetailBinding
import com.yeonproject.dodam_mvvm.ext.glideImageSet
import com.yeonproject.dodam_mvvm.view.base.BaseActivity
import java.util.*

class WordDetailActivity : BaseActivity<ActivityWordDetailBinding>(R.layout.activity_word_detail) {
    private val tts = TextToSpeech(App.instance.context(), TextToSpeech.OnInitListener {
        if (it != TextToSpeech.ERROR) {
            setting()
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var myPaint = WordPaint(this)
        binding.linear.addView(myPaint)

        binding.tvName.text = intent.getStringExtra(NAME).toLowerCase()
        binding.ivImage.glideImageSet(
            intent.getStringExtra(IMAGE)
        )

        binding.btnSound.setOnClickListener {
            tts.speak(binding.tvName.text.toString(), TextToSpeech.QUEUE_FLUSH, null, null)
        }

        binding.btnBack.setOnClickListener {
            finish()
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
            myPaint = WordPaint(this)
            binding.linear.addView(myPaint)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.stop()
        tts.shutdown()
    }

    private fun setting() {
        val language = intent.getStringExtra(LANGUAGE)
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
    }
}