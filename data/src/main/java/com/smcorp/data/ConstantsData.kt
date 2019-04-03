package com.smcorp.data

interface ConstantsData {

    interface Preferences {
        companion object {
            const val SHARED_PREFERENCES_KEY = "shared_preferences_key"
        }

        interface Keys {
            companion object {
                const val API_TOKEN_KEY = "shared_preferences_api_token_key"
            }
        }
    }

    interface Auth {
        companion object {
            const val API_AUTH = "Authorization"
        }
    }
}