package ru.sibsutis.table.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.sibsutis.table.feature.groupmenu.ui.StartingGroupMenuScreen

@Composable
fun GlobalController(startDestination: String) {
	val navController = rememberNavController()

	NavHost(
		navController = navController,
		startDestination = startDestination
	) {
		StartingGroupMenuScreen.route(this, navController)
	}
}