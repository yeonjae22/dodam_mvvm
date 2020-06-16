package com.yeonproject.dodam_mvvm.data.repository.my_word

import com.yeonproject.dodam_mvvm.data.repository.Callback
import com.yeonproject.dodam_mvvm.data.room.entity.MyWordEntity
import com.yeonproject.dodam_mvvm.data.source.local.my_word.MyWordLocalDataSource

class MyWordRepositoryImpl(private val localDataSource: MyWordLocalDataSource) :
    MyWordRepository {
    override fun createMyWord(
        wordNumber: Int,
        hangul: String,
        english: String,
        image: String,
        callback: Callback<Boolean>
    ) {
        localDataSource.createMyWord(wordNumber, hangul, english, image, callback)
    }

    override fun getMyWord(callback: Callback<List<MyWordEntity>>) {
        localDataSource.getMyWord(callback)
    }

    override fun getMyWordDetail(wordNumber: Int, callback: Callback<MyWordEntity>) {
        localDataSource.getMyWordDetail(wordNumber, callback)
    }

    override fun updateMyWord(
        wordNumber: Int,
        hangul: String,
        english: String,
        image: String,
        callback: Callback<Boolean>
    ) {
        localDataSource.updateMyWord(wordNumber, hangul, english, image, callback)
    }

    override fun updateMyWord(
        wordNumber: Int,
        hangul: String,
        english: String,
        callback: Callback<Boolean>
    ) {
        localDataSource.updateMyWord(wordNumber, hangul, english, callback)
    }

    override fun deleteMyWord(wordNumber: Int, callback: Callback<Boolean>) {
        localDataSource.deleteMyWord(wordNumber, callback)
    }

    companion object {
        fun getInstance(localDataSource: MyWordLocalDataSource): MyWordRepository =
            MyWordRepositoryImpl(localDataSource)
    }
}