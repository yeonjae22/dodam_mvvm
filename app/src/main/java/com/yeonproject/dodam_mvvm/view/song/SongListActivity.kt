package com.yeonproject.dodam_mvvm.view.song

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yeonproject.dodam_mvvm.R
import com.yeonproject.dodam_mvvm.databinding.ActivitySongListBinding
import com.yeonproject.dodam_mvvm.network.model.SongResponse
import com.yeonproject.dodam_mvvm.view.base.BaseActivity
import com.yeonproject.dodam_mvvm.view.song.adapter.SongAdapter
import com.yeonproject.dodam_mvvm.view.view_model.SongViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SongListActivity : BaseActivity<ActivitySongListBinding>(R.layout.activity_song_list) {
    private var songAdapter = SongAdapter()
    private val viewModel by viewModel<SongViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getSongList()
        setupViewModel()

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.rvSongList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = songAdapter
        }

        songAdapter.setOnClickListener(object :
            SongAdapter.OnClickListener {
            override fun onClick(song: SongResponse) {
                val intent = Intent(applicationContext, SongDetailActivity::class.java)
                intent.putExtra(ID, song.id)
                intent.putExtra(NAME, song.name)
                startActivity(intent)
            }
        })
    }

    private fun setupViewModel() {
        viewModel.songList.observe(this, Observer {
            binding.layoutProgressBar.visibility = View.GONE
            binding.layoutSongList.visibility = View.VISIBLE
            songAdapter.addData(it)
        })
    }

    companion object {
        private const val ID = "id"
        private const val NAME = "name"
    }
}
