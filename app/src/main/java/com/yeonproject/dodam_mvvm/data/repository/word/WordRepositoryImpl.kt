package com.yeonproject.dodam_mvvm.data.repository.word

import com.yeonproject.dodam_mvvm.data.repository.Callback
import com.yeonproject.dodam_mvvm.data.source.remote.word.WordRemoteDataSource
import com.yeonproject.dodam_mvvm.network.model.WordResponse

class WordRepositoryImpl private constructor(private val remoteDataSource: WordRemoteDataSource) :
    WordRepository {
    override fun getWordList(theme: String, callback: Callback<List<WordResponse>>) {
        remoteDataSource.getWordList(theme, callback)
    }

    companion object {
        fun getInstance(remoteDataSource: WordRemoteDataSource): WordRepository =
            WordRepositoryImpl(remoteDataSource)
    }
}