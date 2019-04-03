package com.smcorp.data.model.response

import com.google.gson.annotations.SerializedName

class UsersResponse(@SerializedName("id") var id: Int,
                    @SerializedName("name") var name: String)