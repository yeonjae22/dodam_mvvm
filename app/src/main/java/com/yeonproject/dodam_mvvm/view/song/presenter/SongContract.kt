package com.yeonproject.dodam_mvvm.view.song.presenter

import com.yeonproject.dodam_mvvm.network.model.SongResponse

interface SongContract {

    interface View {
        var presenter: Presenter
        fun showSongList(items: List<SongResponse>)
    }

    interface Presenter {
        fun getSongList()
    }
}