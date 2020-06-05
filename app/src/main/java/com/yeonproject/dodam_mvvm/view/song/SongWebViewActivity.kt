package com.yeonproject.dodam_mvvm.view.song

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import androidx.appcompat.app.AppCompatActivity
import com.yeonproject.dodam_mvvm.R
import kotlinx.android.synthetic.main.activity_song_web_view.*

class SongWebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_web_view)
        val uri = intent.getStringExtra(LINK)
        setWebView("https://$uri")
        finish()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun setWebView(uri: String) {
        web_view.run {
            webChromeClient = WebChromeClient()
            settings.setSupportMultipleWindows(true)
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.useWideViewPort = true
            settings.builtInZoomControls = true
            loadUrl(uri)
        }
    }

    companion object {
        private const val LINK = "link"
    }
}