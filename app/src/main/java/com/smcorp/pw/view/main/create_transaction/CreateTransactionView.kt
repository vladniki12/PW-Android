package com.smcorp.pw.view.main.create_transaction

import com.arellomobile.mvp.MvpView

interface CreateTransactionView: MvpView {
    fun openMainScreen()
    fun showError(id: Int)
    fun showError(message: String)
}