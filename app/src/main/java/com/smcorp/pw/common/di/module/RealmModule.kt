package com.smcorp.pw.common.di.module

import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where
import java.util.*


@Module
class RealmModule {

    @Provides
    internal fun provideRealmConfiguration(): RealmConfiguration {
        return RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
    }

    @Provides
    internal fun provideRealm(realmConfiguration: RealmConfiguration): Realm {
        return Realm.getInstance(realmConfiguration)
    }

    /*
    @Provides
    internal fun provideDeviceId(realm: Realm): DeviceIdDb {
        val idObj = realm.where<DeviceIdDb>().findFirst()
        if (idObj != null) return idObj

        val generatedId = UUID.randomUUID().toString()
        val generatedIdObj = DeviceIdDb(generatedId)
        realm.executeTransaction { r ->
            // Add id
            r.delete(DeviceIdDb::class.java)
            r.copyToRealmOrUpdate(generatedIdObj)
        }
        return generatedIdObj
    }*/
}