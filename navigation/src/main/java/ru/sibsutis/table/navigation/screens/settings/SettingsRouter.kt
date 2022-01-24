package ru.sibsutis.table.navigation.screens.settings

import androidx.navigation.NavController
import ru.sibsutis.table.navigation.screens.changegroup.ChangeGroupNavigationContent

class SettingsRouter(
	private val navigator: NavController
) {

	fun navigateToGroupMenuScreen(currentGroup: String) {
		navigator.navigate(ChangeGroupNavigationContent.createPath(currentGroup))
	}

	fun navigateToAboutAppScreen() {
		// TODO
	}
}