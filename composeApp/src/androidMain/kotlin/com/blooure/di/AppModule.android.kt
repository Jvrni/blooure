package com.blooure.di

import com.blooure.getDatabaseBuilder
import com.data.local.AppDatabase
import com.data.local.getAppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<AppDatabase> {
        val builder = getDatabaseBuilder(context = get())
        getAppDatabase(builder)
    }
}