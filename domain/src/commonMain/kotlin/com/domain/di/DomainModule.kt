package com.domain.di

import com.domain.user.AddUser
import com.domain.user.DeleteUser
import com.domain.user.GetUsers
import org.koin.dsl.module

val provideDomainModule = module {

    single {
        AddUser(get())
    }

    single {
        GetUsers(get())
    }

    single {
        DeleteUser(get())
    }
}