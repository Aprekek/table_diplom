package ru.sibsutis.table.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
	single { provideSharedPreferences(androidApplication()) }
}

private fun provideSharedPreferences(context: Context): SharedPreferences =
	context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)