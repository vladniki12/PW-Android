package com.smcorp.pw.common.mvp

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.smcorp.pw.navigation.AppRouter
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

abstract class PWPresenter<View: MvpView> : MvpPresenter<View>() {

    @Inject
    lateinit var router: AppRouter


    fun routerExit() {
        router.exit()
    }

    open inner class NetworkObserver<T> : DisposableObserver<T>() {
        override fun onNext(resp: T) {
        }

        override fun onComplete() {
        }

        override fun onError(e: Throwable) {

        }
    }
}
