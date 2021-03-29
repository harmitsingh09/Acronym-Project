package com.acronym.app

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.os.StrictMode
import android.util.Log
import com.acronym.app.data.AppPreferences
import dagger.hilt.android.HiltAndroidApp
import net.danlew.android.joda.JodaTimeAndroid

@HiltAndroidApp
class SnippetApplication : Application() {
    private var mInstance: SnippetApplication? = null
    private var apiRemainingLimit = 80

    companion object {
        private var mInstance: SnippetApplication? = null

        fun getInstance(): SnippetApplication? {
            if (mInstance == null) {
                mInstance =
                    SnippetApplication()
            }
            return mInstance
        }
    }


    @SuppressLint("MissingPermission")
    override fun onCreate() {
        super.onCreate()
        mInstance = this
        uriCheckForAPi25()
        JodaTimeAndroid.init(this);
        initPreferences()

    }

    private fun initPreferences(){
        AppPreferences.init(this)
    }


    private fun uriCheckForAPi25() {
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                val m =
                    StrictMode::class.java.getMethod("disableDeathOnFileUriExposure")
                m.invoke(null)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getApiRemainingLimit(): Int {
        Log.i("Application", "api remaining limit $apiRemainingLimit")
        return apiRemainingLimit
    }

}
