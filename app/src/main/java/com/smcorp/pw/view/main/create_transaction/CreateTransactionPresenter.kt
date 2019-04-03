package com.smcorp.pw.view.main.create_transaction

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.presenter.InjectPresenter
import com.smcorp.data.model.request.CreateTransactionBody
import com.smcorp.data.model.response.CreateTransactionResponse
import com.smcorp.data.model.response.UserInfoResponse
import com.smcorp.domain.interactor.Profile.CreateTransactionUseCase
import com.smcorp.domain.repository.TransactionRepository
import com.smcorp.pw.R
import com.smcorp.pw.common.mvp.PWPresenter
import com.smcorp.pw.view.PWApplication
import retrofit2.HttpException
import javax.inject.Inject


@InjectViewState
class CreateTransactionPresenter: PWPresenter<CreateTransactionView>() {


    @Inject lateinit var mCreateTransactionUseCase: CreateTransactionUseCase

    @Inject
    lateinit var mTransactionRepo: TransactionRepository

    var userName: String = ""

    init {
        PWApplication.appComponent.inject(this)
    }

    fun createTransaction(amount: String) {
        mCreateTransactionUseCase.setParams(CreateTransactionBody(userName,amount))
        mCreateTransactionUseCase.execute(CreateTransaction())

    }

    private inner class CreateTransaction : NetworkObserver<CreateTransactionResponse>() {
        override fun onNext(resp: CreateTransactionResponse) {
            mTransactionRepo.subBalance(resp.transaction.amount)
            viewState.openMainScreen()
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