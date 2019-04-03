package com.smcorp.data.extension

import android.content.SharedPreferences
import android.util.Base64
import com.smcorp.data.ConstantsData

val SharedPreferences.tokenIdMap
    get() =  hashMapOf(ConstantsData.Auth.API_AUTH to ("bearer $tokenId"))



val SharedPreferences.tokenId: String?
    get() {
        val token = this.getString(ConstantsData.Preferences.Keys.API_TOKEN_KEY, null)
        token?.let {
            return "$it"
        }
        return null
    }