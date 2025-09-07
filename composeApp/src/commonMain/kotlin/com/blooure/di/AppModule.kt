package com.blooure.di

import org.koin.core.module.Module

fun appModule() = listOf(
    platformModule(),
    provideViewModelModule,
)

expect fun platformModule(): Module