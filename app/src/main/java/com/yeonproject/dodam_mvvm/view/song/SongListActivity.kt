package com.yeonproject.dodam_mvvm.view.song

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.yeonproject.dodam_mvvm.Injection
import com.yeonproject.dodam_mvvm.network.model.SongResponse
import com.yeonproject.dodam_mvvm.view.song.adapter.SongAdapter
import com.yeonproject.dodam_mvvm.view.song.presenter.SongContract
import com.yeonproject.dodam_mvvm.view.song.presenter.SongPresenter
import com.yeonproject.dodam_mvvm.R
import kotlinx.android.synthetic.main.activity_song_list.*

class SongListActivity : AppCompatActivity(), SongContract.View {
    override lateinit var presenter: SongContract.Presenter
    private var songAdapter = SongAdapter()

    override fun showSongList(items: List<SongResponse>) {
        layout_progress_bar.visibility = View.GONE
        layout_song_list.visibility = View.VISIBLE
        songAdapter.addData(items)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)
        presenter = SongPresenter(
            Injection.songRepository(), this
        )
        presenter.getSongList()

        btn_back.setOnClickListener {
            finish()
        }

        rv_song_list?.apply {
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

    companion object {
        private const val ID = "id"
        private const val NAME = "name"
    }
}
