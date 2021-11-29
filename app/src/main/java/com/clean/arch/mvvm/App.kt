package com.clean.arch.mvvm

import android.app.Application
import com.clean.arch.mvvm.fabrics.*

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AppFactory.createInstance(applicationContext)
    }
}