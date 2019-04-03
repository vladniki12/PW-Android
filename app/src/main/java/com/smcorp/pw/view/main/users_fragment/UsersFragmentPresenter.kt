package com.smcorp.pw.view.main.users_fragment

import com.arellomobile.mvp.InjectViewState
import com.smcorp.data.model.request.UsersBody
import com.smcorp.data.model.response.HistoryTransactionResponse
import com.smcorp.data.model.response.UsersResponse
import com.smcorp.domain.interactor.Profile.UsersUseCase
import com.smcorp.pw.R
import com.smcorp.pw.common.mvp.PWPresenter
import com.smcorp.pw.view.PWApplication
import com.smcorp.pw.view.main.create_transaction.CreateTransactionFragment
import retrofit2.HttpException
import javax.inject.Inject

@InjectViewState
class UsersFragmentPresenter: PWPresenter<UsersFragmentView>() {

    @Inject lateinit var mUsersUseCase: UsersUseCase

    var mSearchString = ""


    init {
        PWApplication.appComponent.inject(this)
    }

    fun getUsers(name: String) {
        mUsersUseCase.setParams(UsersBody(name))
        mUsersUseCase.execute(Users())
    }

    private inner class Users : NetworkObserver<List<UsersResponse>>() {
        override fun onNext(resp: List<UsersResponse>) {
           viewState.showUsers(resp)
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

    fun withSearch(stringSearch: String) {
        getUsers(stringSearch)
    }

    fun createTransactionWithName(name: String) {
        router.navigateTo(CreateTransactionFragment.FragmentCreateTransaction(name))
    }

    fun backOnClick() {
        router.exit()
    }

}