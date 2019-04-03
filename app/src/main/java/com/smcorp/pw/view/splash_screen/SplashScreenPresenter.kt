package com.smcorp.pw.view.splash_screen

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.smcorp.domain.repository.ProfileRepository
import javax.inject.Inject


@InjectViewState
class SplashScreenPresenter: MvpPresenter<SplashScreenView>() {

    @Inject lateinit var profileUseCase: ProfileRepository

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        if( profileUseCase.isApiTokenExists()) {
            viewState.enterApplication(true)
        } else {
            viewState.enterApplication(false)
        }
    }
}