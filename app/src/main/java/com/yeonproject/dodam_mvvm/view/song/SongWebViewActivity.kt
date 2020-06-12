package com.yeonproject.dodam_mvvm.view.song

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.databinding.ActivitySongWebViewBinding
import com.yeonproject.dodam_mvvm.view.base.BaseActivity

class SongWebViewActivity :
    BaseActivity<ActivitySongWebViewBinding>(R.layout.activity_song_web_view) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = intent.getStringExtra(LINK)
        setWebView("https://$uri")
        finish()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun setWebView(uri: String) {
        binding.webView.run {
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