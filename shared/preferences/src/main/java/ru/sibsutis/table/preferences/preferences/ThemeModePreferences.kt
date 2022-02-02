package ru.sibsutis.table.preferences.preferences

import android.content.SharedPreferences

class ThemeModePreferences(
	private val sharedPreferences: SharedPreferences
) {

	companion object {

		const val THEME_MODE_KEY = "theme_mode"
	}

	fun saveTheme(themeMode: Int) {
		sharedPreferences.edit().putInt(THEME_MODE_KEY, themeMode).apply()
	}

	fun getThemeMode() = sharedPreferences.getInt(THEME_MODE_KEY, -1)
}