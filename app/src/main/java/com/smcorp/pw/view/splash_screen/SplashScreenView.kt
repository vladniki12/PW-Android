package com.smcorp.pw.view.splash_screen

import com.arellomobile.mvp.MvpView

interface SplashScreenView: MvpView {
    fun enterApplication(isAuth: Boolean)
}