package com.yeonproject.dodam_mvvm.data.source.local.my_word

import com.yeonproject.dodam_mvvm.data.repository.Callback
import com.yeonproject.dodam_mvvm.data.room.entity.MyWordEntity

interface MyWordLocalDataSource {
    fun createMyWord(
        wordNumber: Int,
        hangul: String,
        english: String,
        image: String,
        callback: Callback<Boolean>
    )

    fun getMyWord(callback: Callback<List<MyWordEntity>>)
    fun getMyWordDetail(wordNumber: Int, callback: Callback<MyWordEntity>)
    fun updateMyWord(
        wordNumber: Int,
        hangul: String,
        english: String,
        image: String,
        callback: Callback<Boolean>
    )

    fun updateMyWord(wordNumber: Int, hangul: String, english: String, callback: Callback<Boolean>)
    fun deleteMyWord(wordNumber: Int, callback: Callback<Boolean>)
}