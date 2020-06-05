package com.yeonproject.dodam_mvvm.data.source.remote.word

import com.yeonproject.dodam_mvvm.data.repository.Callback
import com.yeonproject.dodam_mvvm.network.model.WordResponse

interface WordRemoteDataSource {
    fun getWordList(theme: String, callback: Callback<List<WordResponse>>)
}