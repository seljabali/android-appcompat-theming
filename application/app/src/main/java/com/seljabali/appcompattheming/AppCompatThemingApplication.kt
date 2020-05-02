package com.seljabali.appcompattheming

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class AppCompatThemingApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        setupLibraries()
    }

    private fun setupLibraries() {
        Logger.addLogAdapter(AndroidLogAdapter())
        AndroidThreeTen.init(this)
    }
}