package ru.sibsutis.table.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.sibsutis.table.feature.aboutapp.AboutAppScreen
import ru.sibsutis.table.feature.groups.changegroup.ui.ChangeGroupScreen
import ru.sibsutis.table.feature.groups.startingscreen.ui.StartingGroupMenuScreen
import ru.sibsutis.table.feature.teachers.detailsscreen.ui.DetailsScreen
import ru.sibsutis.table.features.mainbottomnavigationscreen.ui.MainBottomNavigationScreen

@Composable
fun GlobalController(startDestination: String, currentGroup: String = "", teacherName: String = "") {
	val navController = rememberNavController()
	NavHost(
		navController = navController,
		startDestination = startDestination
	) {
		StartingGroupMenuScreen.route(this, navController)
		ChangeGroupScreen.route(this, navController)
		MainBottomNavigationScreen.route(this, navController, currentGroup)
		AboutAppScreen.route(this, navController)
		DetailsScreen.route(this, navController, teacherName)
	}
}