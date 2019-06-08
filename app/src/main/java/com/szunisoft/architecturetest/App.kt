package com.szunisoft.architecturetest

import android.app.Application
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin!
        startKoin(this, listOf(
                networkModule,
                remoteDataSourceModule,
                localDataSourceModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
        ))

        // Init Timber
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}