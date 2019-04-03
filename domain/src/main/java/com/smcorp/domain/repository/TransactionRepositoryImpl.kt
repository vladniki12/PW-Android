package com.smcorp.domain.repository

import android.content.SharedPreferences
import com.smcorp.data.extension.tokenIdMap
import com.smcorp.data.model.request.CreateTransactionBody
import com.smcorp.data.model.response.CreateTransactionResponse
import com.smcorp.data.model.response.HistoryTransactionResponse
import com.smcorp.data.network.PWService
import com.smcorp.data.realm.model.UserInfoDB
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmObject
import io.realm.kotlin.where
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val restApiService: PWService,
                                                private val realm: Realm,
                                                private val preferences: SharedPreferences
) : TransactionRepository {
    override fun getHistoryTransaction(): Observable<HistoryTransactionResponse> {
        return restApiService.getTransaction(preferences.tokenIdMap)
    }

    override fun createTransaction(body: CreateTransactionBody): Observable<CreateTransactionResponse> {
        return restApiService.createTransaction(preferences.tokenIdMap,body)
    }

    override fun saveHistoryTransaction() {
    }

    override fun subBalance(amount: Float) {
        realm.executeTransaction {
            var userInfo = it.where<UserInfoDB>().findFirst()

            if(userInfo != null) {

                userInfo.balance = userInfo.balance + amount
            }

            it.copyToRealmOrUpdate(userInfo)
        }
    }

    override fun getHistoryTransactionDB(): List<RealmObject?> {
        return arrayListOf()
    }

}