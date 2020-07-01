package com.yeonproject.dodam_mvvm.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yeonproject.dodam_mvvm.data.model.MyWordItem
import com.yeonproject.dodam_mvvm.util.App

@Entity(tableName = "word")
data class MyWordEntity(
    @PrimaryKey(autoGenerate = true) val wordNumber: Int,
    @ColumnInfo(name = "hangul") val hangul: String,
    @ColumnInfo(name = "english") val english: String,
    @ColumnInfo(name = "image") val image: String
) {
    fun toMyWordItem(): MyWordItem {
        val image = App.instance.context().filesDir.absoluteFile.toString() + "/" + image
        return MyWordItem(
            wordNumber,
            hangul,
            english,
            image
        )
    }
}