package com.yeonproject.dodam_mvvm.view.song

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yeonproject.dodam_mvvm.R
import kotlinx.android.synthetic.main.activity_song_detail.*

class SongDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)
        val id = intent.getStringExtra(ID)
        val name = intent.getStringExtra(NAME)

        tv_name.text = name
        tv_link.text = "www.youtube.com/watch?v=$id"

        val youTubePlayerView = you_tube_player_view
        if (id != null) {
            youTubePlayerView.play(id)
        }

        btn_back.setOnClickListener {
            finish()
        }

        tv_link.setOnClickListener {
            val intent = Intent(this, SongWebViewActivity::class.java)
            intent.putExtra(LINK, tv_link.text.toString())
            startActivity(intent)
        }
    }

    companion object {
        private const val ID = "id"
        private const val NAME = "name"
        private const val LINK = "link"
    }
}