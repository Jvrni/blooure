package com.blooure.di

import com.data.di.provideServiceModule
import com.domain.di.provideDomainModule
import org.koin.core.module.Module

/**
 * Combines all Koin modules for the application.
 *
 * This function aggregates modules from different layers of the application:
 * - `platformModule()`: Provides platform-specific dependencies.
 * - `provideViewModelModule`: Provides ViewModels for the presentation layer.
 * - `provideDomainModule`: Provides use cases and entities for the domain layer.
 * - `provideServiceModule`: Provides data sources and repositories for the data layer.
 *
 * @return A list of Koin [Module]s to be loaded by the Koin application.
 */
fun appModule() = listOf(
    platformModule(),
    provideViewModelModule,
    provideDomainModule,
    provideServiceModule
)

expect fun platformModule(): Module