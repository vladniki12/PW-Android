package com.smcorp.data.model.response

import com.google.gson.annotations.SerializedName

class UserInfoResponse(
    @SerializedName("user_info_token") val userInfoToken: UserInfoToken

)

class UserInfoToken(
    @SerializedName("id") val id: Int,
                    @SerializedName("name") val name: String,
                    @SerializedName("email") val email: String,
                    @SerializedName("balance") val balance: Float)