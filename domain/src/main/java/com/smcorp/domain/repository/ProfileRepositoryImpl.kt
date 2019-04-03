package com.smcorp.domain.repository

import android.content.SharedPreferences
import com.smcorp.data.ConstantsData
import com.smcorp.data.extension.tokenId
import com.smcorp.data.extension.tokenIdMap
import com.smcorp.data.model.request.SignInBody
import com.smcorp.data.model.request.SignUpBody
import com.smcorp.data.model.response.TokenResponse
import com.smcorp.data.model.response.UserInfoResponse
import com.smcorp.data.model.response.UserInfoToken
import com.smcorp.data.network.PWService
import com.smcorp.data.realm.model.UserInfoDB
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmObject
import io.realm.kotlin.where
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val restApiService: PWService,
                                                private val realm: Realm,
                                                private val preferences: SharedPreferences
) : ProfileRepository {

    //API
    override fun signUp(body: SignUpBody): Observable<TokenResponse> {
        return restApiService.signUpUser(body)
    }

    override fun getUserInfo(): Observable<UserInfoResponse> {
        return restApiService.getUserInfo(preferences.tokenIdMap)
    }

    override fun signIn(body: SignInBody): Observable<TokenResponse> {
        return restApiService.signInUser(body)
    }


    //DB
    override fun isApiTokenExists(): Boolean {

        return !preferences.tokenId.isNullOrEmpty()
    }

    override fun saveToken(token: String) {
        val editPreferences = preferences.edit()
        editPreferences.putString(ConstantsData.Preferences.Keys.API_TOKEN_KEY, token)
        editPreferences.apply()
    }

    override fun saveUserInfo(userInfoToken: UserInfoToken) {
        realm.executeTransaction {
            var userInfo = it.where<UserInfoDB>().findFirst()

            if(userInfo == null) {
                userInfo = UserInfoDB(userInfoToken.id)
            }
            userInfo.name = userInfoToken.name
            userInfo.email = userInfoToken.email
            userInfo.balance = userInfoToken.balance

            it.copyToRealmOrUpdate(userInfo)
        }
    }

    override fun getUserInfoDB(): RealmObject? {
        return realm.where<UserInfoDB>().findFirst()
    }

    override fun subBalance(amount: Float) {
        realm.executeTransaction {
            var userInfo = it.where<UserInfoDB>().findFirst()

            if(userInfo != null) {
                userInfo.balance = userInfo.balance - amount

                it.copyToRealmOrUpdate(userInfo)
            }
        }
    }

    override fun logout() {
        val editPreferences = preferences.edit()
        editPreferences.putString(ConstantsData.Preferences.Keys.API_TOKEN_KEY, "")
        editPreferences.apply()

        realm.executeTransaction { r->
            r.deleteAll()
        }
    }

}
