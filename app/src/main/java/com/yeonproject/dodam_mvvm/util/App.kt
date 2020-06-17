package com.yeonproject.dodam_mvvm.util

import android.app.Application
import android.content.Context
import com.yeonproject.dodam_mvvm.di.exampleModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(applicationContext)
            modules(listOf(exampleModule))
        }
    }

    fun context(): Context = applicationContext
}