package com.smcorp.pw.common.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.smcorp.domain.executor.JobExecutor
import com.smcorp.domain.executor.PostExecutionThread
import com.smcorp.domain.executor.ThreadExecutor
import com.smcorp.pw.common.Constants
import com.smcorp.pw.common.executor.UIThread
import dagger.Module
import dagger.Provides

@Module
class AppModule(private  val  mApplication: Application) {
    @Provides
    internal fun providesApplication(): Application = mApplication

    @Provides
    internal fun provideApplicationContext() = mApplication

    @Provides
    internal fun provideThreadExecutor(): ThreadExecutor = JobExecutor()

    @Provides
    internal fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    internal fun provideSharedPreferences(): SharedPreferences {
        return mApplication.getSharedPreferences(Constants.Preferences.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
    }
}