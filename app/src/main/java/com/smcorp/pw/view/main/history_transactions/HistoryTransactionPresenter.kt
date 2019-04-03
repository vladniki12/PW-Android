package com.smcorp.pw.view.main.history_transactions

import com.arellomobile.mvp.InjectViewState
import com.smcorp.data.model.response.CreateTransactionResponse
import com.smcorp.data.model.response.HistoryTransactionResponse
import com.smcorp.domain.interactor.Profile.HistoryTransactionUseCase
import com.smcorp.pw.R
import com.smcorp.pw.common.mvp.PWPresenter
import com.smcorp.pw.view.PWApplication
import retrofit2.HttpException
import javax.inject.Inject

@InjectViewState
class HistoryTransactionPresenter: PWPresenter<HistoryTransactionView>() {

    @Inject lateinit var mHistoryTransaction: HistoryTransactionUseCase

    init {
        PWApplication.appComponent.inject(this)
    }

    fun getHistory() {
        mHistoryTransaction.execute(HistoryTransaction())
    }

    private inner class HistoryTransaction : NetworkObserver<HistoryTransactionResponse>() {
        override fun onNext(resp: HistoryTransactionResponse) {
            viewState.showHistory(resp.transactions)

        }

        override fun onError(e: Throwable) {
            if(e is HttpException){
                when(e.code()){
                    400 -> {
                        viewState.showError(e.message())
                    }
                    401 -> {
                        viewState.showError(e.message())
                    }
                }
            }
        }


    }

    fun backOnClick() {
        router.exit()
    }

}
