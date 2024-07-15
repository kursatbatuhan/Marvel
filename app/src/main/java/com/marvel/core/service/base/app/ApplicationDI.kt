package com.marvel.core.service.base.app

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationDI: Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
        appContext = applicationContext
        injectMultiDex()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    private fun injectMultiDex() {
        MultiDex.install(this)
    }

    companion object {
        @JvmField
        var appInstance: ApplicationDI? = null

        @JvmStatic
        fun getAppInstance(): ApplicationDI {
            return appInstance as ApplicationDI
        }

        lateinit var appContext: Context
    }
}