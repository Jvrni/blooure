package com.blooure.di

import com.blooure.features.bloodPressure.BloodPressureViewModel
import com.blooure.features.home.HomeViewModel
import com.blooure.features.user.add.AddUserViewModel
import com.blooure.features.user.list.UserListViewModel
import org.koin.dsl.module

/**
 * Koin module for providing ViewModel instances.
 * This module defines how ViewModels are created and injected throughout the application.
 */
val provideViewModelModule = module {

    single {
        HomeViewModel(get(), get())
    }

    single {
        BloodPressureViewModel(get(), get())
    }

    single {
        UserListViewModel(get(), get(), get())
    }

    single {
        AddUserViewModel(get())
    }
}