package com.blooure

import android.app.Application
import org.koin.android.ext.koin.androidContext

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin(appDeclaration = { androidContext(this@MainApplication) })
    }
}