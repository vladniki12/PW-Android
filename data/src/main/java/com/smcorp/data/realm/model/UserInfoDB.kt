package com.smcorp.data.realm.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserInfoDB(
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var balance: Float = 0.0f,
    var email: String = ""
): RealmObject()