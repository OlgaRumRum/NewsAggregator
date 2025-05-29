package com.example.newsaggregator

import android.app.Application
import com.example.newsaggregator.di.dataModule
import com.example.newsaggregator.di.repositoryModule
import com.example.newsaggregator.di.useCaseModule
import com.example.newsaggregator.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(dataModule, repositoryModule, viewModelModule, useCaseModule)
        }
    }
}