package com.smcorp.domain.interactor.Profile

import com.smcorp.data.model.request.SignUpBody
import com.smcorp.data.model.request.UsersBody
import com.smcorp.data.model.response.TokenResponse
import com.smcorp.data.model.response.UsersResponse
import com.smcorp.domain.executor.PostExecutionThread
import com.smcorp.domain.executor.ThreadExecutor
import com.smcorp.domain.interactor.UseCase
import com.smcorp.domain.repository.ProfileRepository
import com.smcorp.domain.repository.UsersRepository
import io.reactivex.Observable
import javax.inject.Inject

class UsersUseCase @Inject constructor(private val mRepository: UsersRepository,
                                       threadExecutor: ThreadExecutor,
                                       postExecutionThread: PostExecutionThread
) :
    UseCase<List<UsersResponse>>(threadExecutor, postExecutionThread) {


    var body: UsersBody? = null

    fun setParams(usersBody: UsersBody) {
        body = usersBody
    }

    override fun buildUseCaseObservable(): Observable<List<UsersResponse>> {
        return mRepository.getUsers(body!!)
    }
}