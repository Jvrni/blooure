package com.blooure.di

import com.blooure.features.home.HomeViewModel
import com.blooure.features.user.add.AddUserViewModel
import com.blooure.features.user.list.UserListViewModel
import org.koin.dsl.module

val provideViewModelModule = module {

    single {
        HomeViewModel()
    }

    single {
        UserListViewModel(get(), get())
    }

    single {
        AddUserViewModel(get())
    }
}