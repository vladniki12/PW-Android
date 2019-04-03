package com.smcorp.domain.interactor.Profile

import com.smcorp.data.model.request.CreateTransactionBody
import com.smcorp.data.model.request.SignInBody
import com.smcorp.data.model.response.CreateTransactionResponse
import com.smcorp.data.model.response.TokenResponse
import com.smcorp.domain.executor.PostExecutionThread
import com.smcorp.domain.executor.ThreadExecutor
import com.smcorp.domain.interactor.UseCase
import com.smcorp.domain.repository.ProfileRepository
import com.smcorp.domain.repository.TransactionRepository
import io.reactivex.Observable
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(private val mRepository: TransactionRepository,
                                                   threadExecutor: ThreadExecutor,
                                                   postExecutionThread: PostExecutionThread
) :
    UseCase<CreateTransactionResponse>(threadExecutor, postExecutionThread) {


    var body: CreateTransactionBody? = null

    fun setParams(createTransactionBody: CreateTransactionBody) {
        body = createTransactionBody
    }

    override fun buildUseCaseObservable(): Observable<CreateTransactionResponse> {
        return mRepository.createTransaction(body!!)
    }
}