package com.smcorp.pw.view.login.signup

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.smcorp.data.model.request.SignInBody
import com.smcorp.data.model.request.SignUpBody
import com.smcorp.data.model.response.TokenResponse
import com.smcorp.domain.interactor.Profile.SignUpUseCase
import com.smcorp.domain.repository.ProfileRepository
import com.smcorp.pw.R
import com.smcorp.pw.common.mvp.PWPresenter
import com.smcorp.pw.view.PWApplication
import retrofit2.HttpException
import javax.inject.Inject

@InjectViewState
class SignUpPresenter: PWPresenter<SignUpView>() {

    @Inject lateinit var mSignUpUseCase: SignUpUseCase
    @Inject lateinit var mProfileRepo: ProfileRepository

    init {
        PWApplication.appComponent.inject(this)
    }

    private fun isValidFields(email: String,username: String, password: String, confirmPassword: String): Boolean {
        if(email.isNullOrEmpty()) {
            viewState.showError(R.string.error_empty_email)
            return false
        }

        if(username.isNullOrEmpty()) {
            viewState.showError(R.string.error_empty_name)
            return false
        }

        if( password.isNullOrEmpty()) {
            viewState.showError(R.string.error_empty_password)
            return false
        }

        if( confirmPassword.isNullOrEmpty()) {
            viewState.showError(R.string.error_empty_conform_password)
            return false
        }

        if(password != confirmPassword) {
            viewState.showError(R.string.error_contains_password)
            return false
        }
        return true
    }

    fun signUp(email: String,username: String, password: String, confirmPassword: String) {
        if(isValidFields(email, username,password,confirmPassword)) {
            mSignUpUseCase.setParams(SignUpBody(username, email, password))
            mSignUpUseCase.execute(SignUpObserver())
        }
    }


    private inner class SignUpObserver : NetworkObserver<TokenResponse>() {
        override fun onNext(resp: TokenResponse) {
            if(!resp.idToken.isNullOrEmpty()) {
                mProfileRepo.saveToken(resp.idToken)
                viewState.openMainScreen()

            }
        }

        override fun onError(e: Throwable) {
            if(e is HttpException){
                when(e.code()){
                    400 -> {
                        viewState.showError(e.message())
                    }
                    401 -> {
                        viewState.showError(R.string.error_internal_server)
                    }
                }
            }
        }


    }

    fun backOnClick() {
        router.exit()
    }
}