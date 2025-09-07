package com.data.di

import com.domain.user.UserRepository
import com.data.repository.UserRepositoryImpl
import com.data.local.AppDatabase
import com.data.local.UserDao
import org.koin.dsl.module

/**
 * Koin module responsible for providing the service layer dependencies.
 *
 * This module configures the dependency injection for the [UserRepository] interface.
 * It defines a factory that creates an instance of [UserRepositoryImpl] and exposes it as a [UserRepository].
 * The [UserRepositoryImpl] instance is created with a dependency injected from Koin, resolved via `get()`.
 *
 * @property factory Defines a factory function that creates and returns a [UserRepository] instance.
 * @constructor Creates a new instance of the service module.
 */
val provideServiceModule = module {
    factory { UserRepositoryImpl(get()) as UserRepository }

    single<UserDao> { get<AppDatabase>().getUserDao() }
}
