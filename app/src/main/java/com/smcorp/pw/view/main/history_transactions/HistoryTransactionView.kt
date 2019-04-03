package com.smcorp.pw.view.main.history_transactions

import com.arellomobile.mvp.MvpView
import com.smcorp.data.model.response.Transaction

interface HistoryTransactionView: MvpView {
    fun showHistory(list: List<Transaction>)
    fun showError(id: Int)
    fun showError(message: String)
}