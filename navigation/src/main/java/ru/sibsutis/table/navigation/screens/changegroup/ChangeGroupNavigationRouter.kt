package ru.sibsutis.table.navigation.screens.changegroup

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import ru.sibsutis.table.navigation.screens.mainbottomnavigation.MainBottomNavigationContent

class ChangeGroupNavigationRouter(
	private val navController: NavController
) {

	fun navigateToMainBottomNavigation(newSelectedGroup: String) {
		navController.navigate(
			route = MainBottomNavigationContent.createPath(newSelectedGroup),
			navOptions = NavOptions.Builder().setPopUpTo(MainBottomNavigationContent.path, inclusive = true).build()
		)
	}

	fun popToMainBottomNavigation() {
		navController.popBackStack(route = MainBottomNavigationContent.path, inclusive = false)
	}
}