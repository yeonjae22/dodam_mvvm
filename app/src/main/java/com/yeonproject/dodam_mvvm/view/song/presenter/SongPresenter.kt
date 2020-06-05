package com.yeonproject.dodam_mvvm.view.song.presenter

import com.yeonproject.dodam_mvvm.data.repository.Callback
import com.yeonproject.dodam_mvvm.data.repository.song.SongRepository
import com.yeonproject.dodam_mvvm.network.model.SongResponse

class SongPresenter(
    private val repository: SongRepository,
    private val view: SongContract.View
) : SongContract.Presenter {
    override fun getSongList() {
        repository.getSongList(object : Callback<List<SongResponse>> {
            override fun onSuccess(response: List<SongResponse>) {
                view.showSongList(response)
            }

            override fun onFailure(message: String) {

            }

        })
    }
}