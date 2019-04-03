package com.smcorp.domain.repository

import com.smcorp.data.model.request.CreateTransactionBody
import com.smcorp.data.model.request.SignInBody
import com.smcorp.data.model.request.SignUpBody
import com.smcorp.data.model.response.*
import io.reactivex.Observable
import io.realm.RealmObject

interface TransactionRepository {

    //api
    fun getHistoryTransaction(): Observable<HistoryTransactionResponse>

    fun createTransaction(body: CreateTransactionBody): Observable<CreateTransactionResponse>

    //
    fun saveHistoryTransaction()

    fun subBalance(amount: Float)
    fun getHistoryTransactionDB(): List<RealmObject?>

}