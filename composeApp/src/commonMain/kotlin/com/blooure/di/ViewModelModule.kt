package com.blooure.di

import com.blooure.features.home.HomeViewModel
import com.blooure.features.user.UserListViewModel
import org.koin.dsl.module

val provideViewModelModule = module {

    single {
        HomeViewModel()
    }

    single {
        UserListViewModel()
    }
}