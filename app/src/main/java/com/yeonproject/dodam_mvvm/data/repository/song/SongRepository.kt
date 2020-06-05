package com.yeonproject.dodam_mvvm.data.repository.song

import com.yeonproject.dodam_mvvm.data.repository.Callback
import com.yeonproject.dodam_mvvm.network.model.SongResponse

interface SongRepository {
    fun getSongList(callback: Callback<List<SongResponse>>)
}