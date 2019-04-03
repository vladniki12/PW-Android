package com.smcorp.data.model.response

import com.google.gson.annotations.SerializedName

class TokenResponse(@SerializedName("id_token") val idToken: String)