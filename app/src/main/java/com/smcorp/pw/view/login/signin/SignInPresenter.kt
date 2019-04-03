package com.smcorp.pw.view.login.signin

import androidx.fragment.app.Fragment
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.smcorp.data.model.request.SignInBody
import com.smcorp.data.model.response.TokenResponse
import com.smcorp.domain.interactor.Profile.SignInUseCase
import com.smcorp.domain.repository.ProfileRepository
import com.smcorp.pw.R
import com.smcorp.pw.common.mvp.PWPresenter
import com.smcorp.pw.navigation.Screens
import com.smcorp.pw.view.PWApplication
import com.smcorp.pw.view.login.signup.SignUpFragment
import retrofit2.HttpException
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

@InjectViewState
class SignInPresenter: PWPresenter<SignInView>() {


    @Inject lateinit var mSignInUseCase: SignInUseCase
    @Inject lateinit var mProfileRepo: ProfileRepository

    init {
        PWApplication.appComponent.inject(this)
    }
    fun signIn() {

    }

    fun signUp() {


        router.navigateTo(SignUpScreen())
    }
    private fun isValidFields(email: String, password: String): Boolean {
        if(email.isNullOrEmpty()) {
            viewState.showError(R.string.error_empty_email)
            return false
        }
        if( password.isNullOrEmpty()) {
            viewState.showError(R.string.error_empty_password)
            return false
        }
        return true
    }

    fun signIn(email: String, password: String) {

        if(isValidFields(email,password)) {
            mSignInUseCase.setParams(SignInBody(email, password))
            mSignInUseCase.execute(SignInObserver())
        }
    }


    private inner class SignInObserver : NetworkObserver<TokenResponse>() {
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


    class SignUpScreen: SupportAppScreen() {


        override fun getFragment(): Fragment {
            return SignUpFragment()

        }
    }

}