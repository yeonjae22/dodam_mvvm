package com.yeonproject.dodam_mvvm.data.repository.word

import com.yeonproject.dodam_mvvm.data.repository.Callback
import com.yeonproject.dodam_mvvm.network.model.WordResponse

interface WordRepository {
    fun getWordList(theme: String, callback: Callback<List<WordResponse>>)
}