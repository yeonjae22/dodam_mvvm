package com.yeonproject.dodam_mvvm

import com.yeonproject.dodam_mvvm.App
import com.yeonproject.dodam_mvvm.data.repository.my_word.MyWordRepository
import com.yeonproject.dodam_mvvm.data.repository.my_word.MyWordRepositoryImpl
import com.yeonproject.dodam_mvvm.data.repository.song.SongRepository
import com.yeonproject.dodam_mvvm.data.repository.song.SongRepositoryImpl
import com.yeonproject.dodam_mvvm.data.repository.word.WordRepository
import com.yeonproject.dodam_mvvm.data.repository.word.WordRepositoryImpl
import com.yeonproject.dodam_mvvm.data.room.database.MyWordDatabase
import com.yeonproject.dodam_mvvm.data.source.local.my_word.MyWordLocalDataSourceImpl
import com.yeonproject.dodam_mvvm.data.source.remote.song.SongRemoteDataSourceImpl
import com.yeonproject.dodam_mvvm.data.source.remote.word.WordRemoteDataSourceImpl
import com.yeonproject.dodam_mvvm.util.AppExecutors

object Injection {
    fun wordRepository(): WordRepository {
        return WordRepositoryImpl.getInstance(
            WordRemoteDataSourceImpl.getInstance()
        )
    }

    fun songRepository(): SongRepository {
        return SongRepositoryImpl.getInstance(
            SongRemoteDataSourceImpl.getInstance()
        )
    }

    fun myWordRepository(): MyWordRepository {
        return MyWordRepositoryImpl.getInstance(
            MyWordLocalDataSourceImpl.getInstance(
                AppExecutors(),
                MyWordDatabase.getInstance(App.instance.context())
            )
        )
    }
}