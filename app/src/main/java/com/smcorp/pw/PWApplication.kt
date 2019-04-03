package com.smcorp.pw.view

import android.app.Application
import com.smcorp.pw.common.di.component.AppComponent
import com.smcorp.pw.common.di.component.DaggerAppComponent
import com.smcorp.pw.common.di.module.AppModule
import io.realm.Realm


class PWApplication: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        Realm.init(this)

    }
}

