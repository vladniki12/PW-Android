package com.smcorp.domain.interactor.Profile

import com.smcorp.data.model.request.SignInBody
import com.smcorp.data.model.response.TokenResponse
import com.smcorp.domain.executor.PostExecutionThread
import com.smcorp.domain.executor.ThreadExecutor
import com.smcorp.domain.interactor.UseCase
import com.smcorp.domain.repository.ProfileRepository
import io.reactivex.Observable
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val mRepository: ProfileRepository,
                                            threadExecutor: ThreadExecutor,
                                            postExecutionThread: PostExecutionThread
) :
    UseCase<TokenResponse>(threadExecutor, postExecutionThread) {


    var body: SignInBody? = null

    fun setParams(signInBody: SignInBody) {
        body = signInBody
    }

    override fun buildUseCaseObservable(): Observable<TokenResponse> {
        return mRepository.signIn(body!!)
    }
}