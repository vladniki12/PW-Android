package com.smcorp.pw.view.login.signup

import com.arellomobile.mvp.MvpView

interface  SignUpView: MvpView{
    fun openMainScreen()

    fun showError(id: Int)
    fun showError(message: String)
}