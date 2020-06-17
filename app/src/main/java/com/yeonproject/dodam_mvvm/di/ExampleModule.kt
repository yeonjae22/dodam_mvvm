package com.yeonproject.dodam_mvvm.di

import com.yeonproject.dodam_mvvm.data.repository.my_word.MyWordRepository
import com.yeonproject.dodam_mvvm.data.repository.my_word.MyWordRepositoryImpl
import com.yeonproject.dodam_mvvm.data.repository.song.SongRepository
import com.yeonproject.dodam_mvvm.data.repository.song.SongRepositoryImpl
import com.yeonproject.dodam_mvvm.data.repository.word.WordRepository
import com.yeonproject.dodam_mvvm.data.repository.word.WordRepositoryImpl
import com.yeonproject.dodam_mvvm.data.source.local.my_word.MyWordLocalDataSource
import com.yeonproject.dodam_mvvm.data.source.local.my_word.MyWordLocalDataSourceImpl
import com.yeonproject.dodam_mvvm.data.source.remote.song.SongRemoteDataSource
import com.yeonproject.dodam_mvvm.data.source.remote.song.SongRemoteDataSourceImpl
import com.yeonproject.dodam_mvvm.data.source.remote.word.WordRemoteDataSource
import com.yeonproject.dodam_mvvm.data.source.remote.word.WordRemoteDataSourceImpl
import com.yeonproject.dodam_mvvm.view.view_model.SongViewModel
import com.yeonproject.dodam_mvvm.view.view_model.WordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val exampleModule = module {
    single<MyWordLocalDataSource> {
        MyWordLocalDataSourceImpl(get(), get())
    }
    single<WordRemoteDataSource> {
        WordRemoteDataSourceImpl()
    }
    single<SongRemoteDataSource> {
        SongRemoteDataSourceImpl()
    }
    single<MyWordRepository> { MyWordRepositoryImpl(get()) }
    single<WordRepository> { WordRepositoryImpl(get()) }
    single<SongRepository> { SongRepositoryImpl(get()) }

    viewModel { WordViewModel(get()) }
    viewModel { SongViewModel(get()) }
}