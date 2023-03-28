package com.example.wordsfactory

import android.app.Application
import android.content.Context

class WordsFactoryApplication: Application() {
    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    fun getAppContext(): Context? {
        return context
    }
}