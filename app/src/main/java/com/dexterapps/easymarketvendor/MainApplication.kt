package com.dexterapps.easymarketvendor


import android.app.Application
import android.content.Context
import android.content.SharedPreferences


class MainApplication : Application() {

    companion object {
        var ctx: Context? = null
        lateinit var sharedPreferencesUser: SharedPreferences
        lateinit var sharedPreferencesApp: SharedPreferences
//        var appEnvironment: AppEnvironment? = null
    }

    override fun onCreate() {
        super.onCreate()
//        appEnvironment = AppEnvironment.SANDBOX
        ctx = applicationContext
//        sharedPreferencesUser = this.getSharedPreferences(
//                Variables.SHARED_PREFERENCE_USER_FILE_NAME,
//                Context.MODE_PRIVATE
//        )

    }


//    fun setAppEnvironment(appEnvironment: AppEnvironment?) {
//        appEnvironment = appEnvironment
//    }

}