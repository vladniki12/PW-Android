package com.smcorp.pw.common.di.module

import com.smcorp.domain.repository.*
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    abstract fun provideTransactionRepository(impl: TransactionRepositoryImpl): TransactionRepository

    @Binds
    abstract fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository
}
