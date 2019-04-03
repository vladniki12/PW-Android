package com.smcorp.pw.common.di.component

import com.smcorp.pw.common.di.module.*
import com.smcorp.pw.common.mvp.PWActivity
import com.smcorp.pw.view.login.signin.SignInPresenter
import com.smcorp.pw.view.login.signup.SignUpPresenter
import com.smcorp.pw.view.main.users_fragment.UsersFragmentPresenter
import com.smcorp.pw.view.main.create_transaction.CreateTransactionPresenter
import com.smcorp.pw.view.main.history_transactions.HistoryTransactionPresenter
import com.smcorp.pw.view.main.main_screen.MainScreenPresenter
import com.smcorp.pw.view.splash_screen.SplashScreenPresenter
import dagger.Component

@Component(modules = [AppModule::class,
    NavigationModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    RealmModule::class])
interface AppComponent {

    //PWActivity
    fun inject(activity: PWActivity)

    //SignInPresenter
    fun inject(presenter: SignInPresenter)

    //SignUpPresenter
    fun inject(presenter: SignUpPresenter)

    //SplashScreenPresenter
    fun inject(presenter: SplashScreenPresenter)

    //MainScreenPresenter
    fun inject(presenter: MainScreenPresenter)

    //HistoryTransactionPresenter
    fun inject(presenter: HistoryTransactionPresenter)

    //CreateTransactionPresenter
    fun inject(presenter: CreateTransactionPresenter)

    //UsersFragmentPresenter
    fun inject(presenter: UsersFragmentPresenter)
}