package com.smcorp.domain.interactor.Profile

import com.smcorp.data.model.request.CreateTransactionBody
import com.smcorp.data.model.response.CreateTransactionResponse
import com.smcorp.data.model.response.HistoryTransactionResponse
import com.smcorp.domain.executor.PostExecutionThread
import com.smcorp.domain.executor.ThreadExecutor
import com.smcorp.domain.interactor.UseCase
import com.smcorp.domain.repository.TransactionRepository
import io.reactivex.Observable
import javax.inject.Inject

class HistoryTransactionUseCase @Inject constructor(private val mRepository: TransactionRepository,
                                                    threadExecutor: ThreadExecutor,
                                                    postExecutionThread: PostExecutionThread
) :
    UseCase<HistoryTransactionResponse>(threadExecutor, postExecutionThread) {


    override fun buildUseCaseObservable(): Observable<HistoryTransactionResponse> {
        return mRepository.getHistoryTransaction()
    }
}