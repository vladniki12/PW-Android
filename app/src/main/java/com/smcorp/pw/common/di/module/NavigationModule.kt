package com.smcorp.pw.common.di.module

import com.smcorp.pw.navigation.AppRouter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder

@Module
class NavigationModule {

    private val cicerone: Cicerone<AppRouter> = Cicerone.create(AppRouter())

    @Provides
    fun provideRouter(): AppRouter = cicerone.router

    @Provides
    fun provideAppNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder
}