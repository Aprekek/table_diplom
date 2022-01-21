package ru.sibsutis.table.navigation.screens.startinggroupmenu

import androidx.navigation.NavController
import ru.sibsutis.table.navigation.screens.mainbottomnavigation.MainBottomNavigationContent

class StartingGroupMenuRouter(
	private val navController: NavController
) {

	fun navigateToMainBottomNavScreen(groupName: String) {
		navController.navigate(
			MainBottomNavigationContent.createPath(groupName)
		)
	}
}