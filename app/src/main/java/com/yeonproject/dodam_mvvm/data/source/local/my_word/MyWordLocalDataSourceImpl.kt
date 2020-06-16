package com.yeonproject.dodam_mvvm.data.source.local.my_word

import com.yeonproject.dodam_mvvm.data.repository.Callback
import com.yeonproject.dodam_mvvm.data.room.database.MyWordDatabase
import com.yeonproject.dodam_mvvm.data.room.entity.MyWordEntity
import com.yeonproject.dodam_mvvm.util.AppExecutors

class MyWordLocalDataSourceImpl(
    private val appExecutors: AppExecutors,
    private val myWordDatabase: MyWordDatabase
) :
    MyWordLocalDataSource {
    override fun createMyWord(
        wordNumber: Int,
        hangul: String,
        english: String,
        image: String,
        callback: Callback<Boolean>
    ) {
        appExecutors.diskIO.execute {
            val newWord = MyWordEntity(
                wordNumber,
                hangul,
                english,
                image
            )
            val insertedPk = myWordDatabase.myWordDao().insertWord(newWord)
            if (insertedPk != null) {
                appExecutors.mainThread.execute {
                    callback.onSuccess(true)
                }
            }
        }
    }

    override fun getMyWord(callback: Callback<List<MyWordEntity>>) {
        appExecutors.diskIO.execute {
            val wordList = myWordDatabase.myWordDao().getWordAll()
            appExecutors.mainThread.execute {
                callback.onSuccess(wordList)
            }
        }
    }

    override fun getMyWordDetail(wordNumber: Int, callback: Callback<MyWordEntity>) {
        appExecutors.diskIO.execute {
            val word = myWordDatabase.myWordDao().getWord(wordNumber)
            appExecutors.mainThread.execute {
                callback.onSuccess(word)
            }
        }
    }

    override fun updateMyWord(
        wordNumber: Int,
        hangul: String,
        english: String,
        image: String,
        callback: Callback<Boolean>
    ) {
        appExecutors.diskIO.execute {
            val updateCount =
                myWordDatabase.myWordDao().updateWord(wordNumber, hangul, english, image)
            if (updateCount == 1) {
                appExecutors.mainThread.execute {
                    callback.onSuccess(true)
                }
            }
        }
    }

    override fun updateMyWord(
        wordNumber: Int,
        hangul: String,
        english: String,
        callback: Callback<Boolean>
    ) {
        appExecutors.diskIO.execute {
            val updateCount =
                myWordDatabase.myWordDao().updateWord(wordNumber, hangul, english)
            if (updateCount == 1) {
                appExecutors.mainThread.execute {
                    callback.onSuccess(true)
                }
            }
        }
    }

    override fun deleteMyWord(wordNumber: Int, callback: Callback<Boolean>) {
        appExecutors.diskIO.execute {
            val deletedCount = myWordDatabase.myWordDao().deleteWord(wordNumber)
            if (deletedCount == 1) {
                appExecutors.mainThread.execute {
                    callback.onSuccess(true)
                }
            } else {
                callback.onSuccess(false)
            }
        }
    }

    companion object {
        fun getInstance(
            appExecutors: AppExecutors,
            myWordDatabase: MyWordDatabase
        ): MyWordLocalDataSource =
            MyWordLocalDataSourceImpl(
                appExecutors,
                myWordDatabase
            )
    }
}