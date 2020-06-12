package com.yeonproject.dodam_mvvm.view.song

import android.content.Intent
import android.os.Bundle
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.databinding.ActivitySongDetailBinding
import com.yeonproject.dodam_mvvm.view.base.BaseActivity

class SongDetailActivity : BaseActivity<ActivitySongDetailBinding>(R.layout.activity_song_detail) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getStringExtra(ID)
        val name = intent.getStringExtra(NAME)

        binding.tvName.text = name
        binding.tvLink.text = "www.youtube.com/watch?v=$id"

        val youTubePlayerView = binding.youTubePlayerView
        if (id != null) {
            youTubePlayerView.play(id)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.tvLink.setOnClickListener {
            val intent = Intent(this, SongWebViewActivity::class.java)
            intent.putExtra(LINK, binding.tvLink.text.toString())
            startActivity(intent)
        }
    }

    companion object {
        private const val ID = "id"
        private const val NAME = "name"
        private const val LINK = "link"
    }
}