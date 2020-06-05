package com.yeonproject.dodam_mvvm.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yeonproject.dodam_mvvm.data.room.entity.MyWordEntity

@Dao
interface MyWordDao {
    @Query("SELECT * FROM word WHERE wordNumber = :wordNumber")
    fun getWord(wordNumber: Int): MyWordEntity

    @Query("SELECT * FROM word")
    fun getWordAll(): List<MyWordEntity>

    @Insert
    fun insertWord(myWordEntity: MyWordEntity): Long

    @Query("UPDATE word SET hangul = :hangul, english = :english, image = :image WHERE wordNumber = :wordNumber")
    fun updateWord(wordNumber: Int, hangul: String, english: String, image: String): Int

    @Query("UPDATE word SET hangul = :hangul, english = :english WHERE wordNumber = :wordNumber")
    fun updateWord(wordNumber: Int, hangul: String, english: String): Int

    @Query("DELETE FROM word WHERE wordNumber = :wordNumber")
    fun deleteWord(wordNumber: Int): Int

}