package com.smcorp.domain.interactor.Profile

import com.smcorp.data.model.request.SignInBody
import com.smcorp.data.model.request.SignUpBody
import com.smcorp.data.model.response.TokenResponse
import com.smcorp.domain.executor.PostExecutionThread
import com.smcorp.domain.executor.ThreadExecutor
import com.smcorp.domain.interactor.UseCase
import com.smcorp.domain.repository.ProfileRepository
import io.reactivex.Observable
import javax.inject.Inject

class SignUpUseCase  @Inject constructor(private val mRepository: ProfileRepository,
                                         threadExecutor: ThreadExecutor,
                                         postExecutionThread: PostExecutionThread
) :
    UseCase<TokenResponse>(threadExecutor, postExecutionThread) {


    var body: SignUpBody? = null

    fun setParams(signUpBody: SignUpBody) {
        body = signUpBody
    }

    override fun buildUseCaseObservable(): Observable<TokenResponse> {
        return mRepository.signUp(body!!)
    }
}