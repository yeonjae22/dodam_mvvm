package com.yeonproject.dodam_mvvm.view.word

import android.graphics.Color
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import com.yeonproject.dodam_mvvm.App
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.ext.glideImageSet
import kotlinx.android.synthetic.main.activity_word_detail.*
import java.util.*

class WordDetailActivity : AppCompatActivity() {
    private val tts = TextToSpeech(App.instance.context(), TextToSpeech.OnInitListener {
        if (it != TextToSpeech.ERROR) {
            setting()
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_detail)
        var myPaint = WordPaint(this)
        linear.addView(myPaint)

        tv_name.text = intent.getStringExtra(NAME).toLowerCase()
        iv_image.glideImageSet(
            intent.getStringExtra(IMAGE),
            iv_image.measuredWidth,
            iv_image.measuredHeight
        )

        btn_sound.setOnClickListener {
            tts.speak(tv_name.text.toString(), TextToSpeech.QUEUE_FLUSH, null, null)
        }

        btn_back.setOnClickListener {
            finish()
        }

        btn_red.setOnClickListener {
            myPaint.setColor(Color.RED)
        }

        btn_blue.setOnClickListener {
            myPaint.setColor(Color.BLUE)
        }

        btn_black.setOnClickListener {
            myPaint.setColor(Color.BLACK)
        }

        btn_eraser.setOnClickListener {
            linear.removeAllViews()
            myPaint = WordPaint(this)
            linear.addView(myPaint)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.stop()
        tts.shutdown()
    }

    private fun setting() {
        val language = intent.getStringExtra(LANGUAGE)
        if(language == "hangul"){
            tts.language = Locale.KOREA
        } else if(language == "english"){
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