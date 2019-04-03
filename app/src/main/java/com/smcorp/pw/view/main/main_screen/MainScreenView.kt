package com.smcorp.pw.view.main.main_screen

import com.arellomobile.mvp.MvpView
import com.smcorp.data.model.response.UserInfoResponse
import com.smcorp.data.model.response.UserInfoToken

interface MainScreenView: MvpView {
    fun updateInfo(userInfo: UserInfoToken)
    fun showError(id: Int)
    fun showError(message: String)
    fun openLoginScreen()
}