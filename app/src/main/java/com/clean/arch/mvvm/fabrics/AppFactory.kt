package com.clean.arch.mvvm.fabrics

import android.content.Context
import java.lang.Exception

object AppFactory {

    private var instance: Context? = null

    fun createInstance(context: Context) {
        instance = context
    }

    fun getInstance(): Context {
        if (instance == null) throw Exception("Not initialize context!")
        return instance!!
    }
}