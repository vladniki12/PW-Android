package com.smcorp.domain.repository

import com.smcorp.data.model.request.SignInBody
import com.smcorp.data.model.request.SignUpBody
import com.smcorp.data.model.response.TokenResponse
import com.smcorp.data.model.response.UserInfoResponse
import com.smcorp.data.model.response.UserInfoToken
import io.reactivex.Observable
import io.realm.RealmObject

interface ProfileRepository {

    //api
    fun signIn(body: SignInBody): Observable<TokenResponse>
    fun signUp(body: SignUpBody): Observable<TokenResponse>
    fun getUserInfo(): Observable<UserInfoResponse>


    //db
    fun isApiTokenExists(): Boolean
    fun getUserInfoDB(): RealmObject?
    fun saveUserInfo(userInfoToken: UserInfoToken)
    fun subBalance(amount: Float)
    fun saveToken(token: String)
    fun logout()

}