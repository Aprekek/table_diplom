package ru.sibsutis.table.preferences.di

import org.koin.dsl.module
import ru.sibsutis.table.preferences.preferences.GroupPreferences

val preferencesModule = module {

	single { GroupPreferences(sharedPreferences = get()) }
}