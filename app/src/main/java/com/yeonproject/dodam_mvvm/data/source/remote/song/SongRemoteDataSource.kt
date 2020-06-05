package com.yeonproject.dodam_mvvm.data.source.remote.song

import com.yeonproject.dodam_mvvm.data.repository.Callback
import com.yeonproject.dodam_mvvm.network.model.SongResponse

interface SongRemoteDataSource {
    fun getSongList(callback: Callback<List<SongResponse>>)
}