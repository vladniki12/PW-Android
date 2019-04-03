package com.smcorp.domain.repository

import android.content.SharedPreferences
import com.smcorp.data.extension.tokenIdMap
import com.smcorp.data.model.request.UsersBody
import com.smcorp.data.model.response.UsersResponse
import com.smcorp.data.network.PWService
import io.reactivex.Observable
import io.realm.Realm
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(private val restApiService: PWService,
                                                    private val realm: Realm,
                                                    private val preferences: SharedPreferences
) : UsersRepository {

    override fun getUsers(body: UsersBody): Observable<List<UsersResponse>> {
        return restApiService.getUsers(preferences.tokenIdMap, body)
    }
}