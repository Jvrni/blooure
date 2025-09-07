package com.blooure.di

import com.data.di.provideServiceModule
import com.domain.di.provideDomainModule
import org.koin.core.module.Module

fun appModule() = listOf(
    platformModule(),
    provideViewModelModule,
    provideDomainModule,
    provideServiceModule
)

expect fun platformModule(): Module