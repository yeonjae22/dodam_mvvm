package com.yeonproject.dodam_mvvm.data.repository.song

import com.yeonproject.dodam_mvvm.data.repository.Callback
import com.yeonproject.dodam_mvvm.data.source.remote.song.SongRemoteDataSource
import com.yeonproject.dodam_mvvm.network.model.SongResponse

class SongRepositoryImpl private constructor(private val remoteDataSource: SongRemoteDataSource) :
    SongRepository {
    override fun getSongList(callback: Callback<List<SongResponse>>) {
        remoteDataSource.getSongList(callback)
    }

    companion object {
        fun getInstance(remoteDataSource: SongRemoteDataSource): SongRepository =
            SongRepositoryImpl(remoteDataSource)
    }
}