package com.smcorp.pw.view.main.main_screen

import android.content.Intent
import com.arellomobile.mvp.InjectViewState
import com.smcorp.data.model.response.TokenResponse
import com.smcorp.data.model.response.UserInfoResponse
import com.smcorp.data.model.response.UserInfoToken
import com.smcorp.data.realm.model.UserInfoDB
import com.smcorp.domain.interactor.Profile.UserInfoUseCase
import com.smcorp.domain.repository.ProfileRepository
import com.smcorp.pw.R
import com.smcorp.pw.common.mvp.PWPresenter
import com.smcorp.pw.view.PWApplication
import com.smcorp.pw.view.login.MainLoginActivity
import com.smcorp.pw.view.main.create_transaction.CreateTransactionFragment
import com.smcorp.pw.view.main.history_transactions.HistoryTransactionFragment
import com.smcorp.pw.view.main.users_fragment.UsersFragment
import retrofit2.HttpException
import javax.inject.Inject

@InjectViewState
class MainScreenPresenter: PWPresenter<MainScreenView>() {

    @Inject lateinit var mUserInfoUseCase: UserInfoUseCase
    @Inject lateinit var mProfileRepo: ProfileRepository

    init {
        PWApplication.appComponent.inject(this)
    }

    fun getInfo() {
        var userInfo = mProfileRepo.getUserInfoDB()
        if( userInfo != null) {
            if( userInfo is UserInfoDB) {
                viewState.updateInfo(UserInfoToken(userInfo.id, userInfo.name, userInfo.email, userInfo.balance))
            }
        }
        mUserInfoUseCase.execute(UserInfo())
    }


    private inner class UserInfo : NetworkObserver<UserInfoResponse>() {
        override fun onNext(resp: UserInfoResponse) {

            viewState.updateInfo(resp.userInfoToken)
            mProfileRepo.saveUserInfo(resp.userInfoToken)
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

    fun openHistory() {
        router.navigateTo(HistoryTransactionFragment.FragmentHistory())
    }

    fun openCreateTransaction() {
        router.navigateTo(UsersFragment.FragmentUsers())
    }

    fun exit() {
        mProfileRepo.logout()

        viewState.openLoginScreen()
    }

}