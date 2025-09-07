package com.blooure.di

import com.blooure.features.home.HomeViewModel
import org.koin.dsl.module

val provideViewModelModule = module {

    single {
        HomeViewModel()
    }
}