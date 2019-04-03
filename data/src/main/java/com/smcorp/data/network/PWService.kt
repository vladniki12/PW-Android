package com.smcorp.data.network

import com.smcorp.data.model.request.CreateTransactionBody
import com.smcorp.data.model.request.SignInBody
import com.smcorp.data.model.request.SignUpBody
import com.smcorp.data.model.request.UsersBody
import com.smcorp.data.model.response.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import java.util.*

interface PWService {

    @POST("users")
    fun signUpUser(@Body signUpBody: SignUpBody): Observable<TokenResponse>

    @POST("sessions/create")
    fun signInUser(@Body signInBody: SignInBody): Observable<TokenResponse>

    @GET("api/protected/transactions")
    fun getTransaction(@HeaderMap headers: Map<String, String>): Observable<HistoryTransactionResponse>

    @POST("api/protected/transactions")
    fun createTransaction(@HeaderMap headers: Map<String, String>,
                          @Body createTransactionBody: CreateTransactionBody)
            : Observable<CreateTransactionResponse>

    @GET("api/protected/user-info")
    fun getUserInfo(@HeaderMap headers: Map<String, String>): Observable<UserInfoResponse>

    @POST("api/protected/users/list")
    fun getUsers(@HeaderMap headers: Map<String, String>, @Body usersBody: UsersBody): Observable<List<UsersResponse>>
}