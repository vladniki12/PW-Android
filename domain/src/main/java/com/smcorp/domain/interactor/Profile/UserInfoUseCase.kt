package com.smcorp.domain.interactor.Profile

import com.smcorp.data.model.request.SignUpBody
import com.smcorp.data.model.response.TokenResponse
import com.smcorp.data.model.response.UserInfoResponse
import com.smcorp.domain.executor.PostExecutionThread
import com.smcorp.domain.executor.ThreadExecutor
import com.smcorp.domain.interactor.UseCase
import com.smcorp.domain.repository.ProfileRepository
import io.reactivex.Observable
import javax.inject.Inject

class UserInfoUseCase @Inject constructor(private val mRepository: ProfileRepository,
                                          threadExecutor: ThreadExecutor,
                                          postExecutionThread: PostExecutionThread
) :
    UseCase<UserInfoResponse>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(): Observable<UserInfoResponse> {
        return mRepository.getUserInfo()
    }
}