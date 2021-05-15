package com.sun.moviedb_54.ultis

import android.app.Application
import com.sun.moviedb_54.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    retrofitModule,
                    viewModelModule,
                    dataSourceModule,
                    repositoryModule,
                    apiModule,
                    storageModule
                )
            )
        }
    }
}
