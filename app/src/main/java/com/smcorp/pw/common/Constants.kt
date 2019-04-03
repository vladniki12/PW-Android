package com.smcorp.pw.common

interface Constants {

    interface Preferences {
        companion object {
            const val SHARED_PREFERENCES_KEY = "shared_preferences_key"
        }
    }

    interface Api {
        companion object {
            const val API_SCHEME = "http://"
            const val API_DOMAIN = "193.124.114.46"
            const val BASE_PORT = ":3001/"
            const val CONNECTION_TIMEOUT_SEC = 30
            const val JSON_DATA_ARRAY_FIELD_NAME = "list"
            const val JSON_DATA_MESSAGES_ARRAY_FIELD_NAME = "messages"
        }
    }
}