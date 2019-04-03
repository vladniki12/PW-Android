package com.smcorp.pw.view.login.signin

import com.arellomobile.mvp.MvpView

interface SignInView: MvpView {
    fun openMainScreen()

    fun showError(id: Int)
    fun showError(message: String)
}