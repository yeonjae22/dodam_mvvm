package com.yeonproject.dodam_mvvm.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
data class MyWordEntity(
    @PrimaryKey(autoGenerate = true) val wordNumber: Int,
    @ColumnInfo(name = "hangul") val hangul: String,
    @ColumnInfo(name = "english") val english: String,
    @ColumnInfo(name = "image") val image: String
)