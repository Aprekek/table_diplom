package ru.sibsutis.table

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.get
import ru.sibsutis.table.navigation.GlobalController
import ru.sibsutis.table.navigation.screens.mainbottomnavigation.MainBottomNavigationContent
import ru.sibsutis.table.navigation.screens.startinggroupmenu.StartingGroupMenuContent
import ru.sibsutis.table.preferences.preferences.GroupPreferences
import ru.sibsutis.table.preferences.preferences.ThemeModePreferences
import ru.sibsutis.table.shared.themes.DiplomTableTheme
import ru.sibsutis.table.shared.themes.DiplomThemeMode
import ru.sibsutis.table.shared.themes.ThemeModes

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		setTheme(R.style.Theme_Table)
		super.onCreate(savedInstanceState)

		restoreTheme()
		val currentGroup = getSavedGroup()

		setContent {
			DiplomTableTheme(DiplomThemeMode.isDarkTheme()) {
				GlobalController(
					startDestination = getStartDestination(currentGroup),
					currentGroup = currentGroup
				)
			}
		}
	}

	private fun getStartDestination(savedGroup: String): String {

		val startingDestination = if (savedGroup.isBlank()) {
			StartingGroupMenuContent.path
		} else {
			MainBottomNavigationContent.path
		}

		return startingDestination
	}

	private fun getSavedGroup() = get<GroupPreferences>().getGroup() ?: ""

	private fun restoreTheme() {
		val savedThemeMode = get<ThemeModePreferences>().getThemeMode()
		DiplomThemeMode.mode = ThemeModes.values().find {
			it.value == savedThemeMode
		} ?: ThemeModes.SYSTEM
	}
}