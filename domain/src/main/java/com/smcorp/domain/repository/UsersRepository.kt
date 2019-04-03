package com.smcorp.domain.repository

import com.smcorp.data.model.request.UsersBody
import com.smcorp.data.model.response.UsersResponse
import io.reactivex.Observable
import java.util.*

interface UsersRepository {
    fun getUsers(body: UsersBody): Observable<List<UsersResponse>>
}