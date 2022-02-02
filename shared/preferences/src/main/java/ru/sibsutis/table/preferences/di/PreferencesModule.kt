package ru.sibsutis.table.preferences.di

import org.koin.dsl.module
import ru.sibsutis.table.preferences.preferences.GroupPreferences
import ru.sibsutis.table.preferences.preferences.ThemeModePreferences

val preferencesModule = module {

	single { GroupPreferences(sharedPreferences = get()) }
	single { ThemeModePreferences(sharedPreferences = get()) }
}