package com.example.android.imdbdemo.app

import android.app.Application
import com.example.android.imdbdemo.modules.appModules
import com.example.android.imdbdemo.modules.repoModule
import com.example.android.imdbdemo.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApplication)
            modules(listOf(appModules, repoModule, viewModelModule))
        }

    }
}