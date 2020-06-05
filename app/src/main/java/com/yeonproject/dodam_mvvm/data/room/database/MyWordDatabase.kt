package com.yeonproject.dodam_mvvm.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yeonproject.dodam_mvvm.data.room.dao.MyWordDao
import com.yeonproject.dodam_mvvm.data.room.entity.MyWordEntity

@Database(entities = [MyWordEntity::class], version = 1)
abstract class MyWordDatabase : RoomDatabase() {
    abstract fun myWordDao(): MyWordDao

    companion object {
        private var INSTANCE: MyWordDatabase? = null

        fun getInstance(context: Context): MyWordDatabase {
            return INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                MyWordDatabase::class.java, "word"
            )
                .fallbackToDestructiveMigration()
                .build()
                .also {
                    INSTANCE = it
                }
        }
    }
}